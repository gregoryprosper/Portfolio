using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Net.NetworkInformation;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Documents;
using System.Windows.Media;
using System.Windows.Controls;
using System.Windows.Media.Imaging;

namespace TwitFeedWPF
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private String bearerToken;
        private String queryParam = "recent";
        private int tweetCount = 20;

        public MainWindow()
        {
            InitializeComponent();
            getAuthenticationToken(false);
            tweetsCountSlider.Value = 20;
        }

        private async void getAuthenticationToken(bool expired)
        {
            if (expired)
            {
                var client = new HttpClient();
                var uri = new Uri("https://api.twitter.com/oauth2/token");

                var encodedConsumerKey = WebUtility.UrlEncode("cLfPzscHFnxCouUD7KQl1F8Il");
                var encodedConsumerSecret = WebUtility.UrlEncode("yeAe5xXU78fBxX5lt19uLPU04J3BRi7ROiijLzMYToqjpX76Sl");
                var combinedKeys = String.Format("{0}:{1}", encodedConsumerKey, encodedConsumerSecret);

                var utfBytes = System.Text.Encoding.UTF8.GetBytes(combinedKeys);
                var encodedString = Convert.ToBase64String(utfBytes);

                client.DefaultRequestHeaders.Add("Authorization", string.Format("Basic {0}", encodedString));

                var data = new List<KeyValuePair<string, string>> 
                { 
                    new KeyValuePair<string, string>("grant_type", "client_credentials") 
                };

                var postData = new FormUrlEncodedContent(data);

                var response = await client.PostAsync(uri, postData);
                AuthenticationResponse authenticationResponse;
                using (response)
                {
                    if (response.StatusCode != System.Net.HttpStatusCode.OK)
                    {
                        throw new Exception("Did not work!");
                    }

                    var content = await response.Content.ReadAsStringAsync();
                    authenticationResponse = JsonConvert.DeserializeObject<AuthenticationResponse>(content);

                    if (authenticationResponse.TokenType != "bearer")
                    {
                        throw new Exception("wrong result type");
                    }

                    this.bearerToken = authenticationResponse.AccessToken;

                    //write to file
                    IFormatter formatter = new BinaryFormatter();
                    Stream saveStream = new FileStream("auth.bin", FileMode.Create);
                    formatter.Serialize(saveStream, authenticationResponse.AccessToken);
                    saveStream.Close();
                }
            }
            else
            {
                if (File.Exists("auth.bin"))
                {
                    IFormatter formatter = new BinaryFormatter();
                    Stream stream = new FileStream("auth.bin", FileMode.Open, FileAccess.Read, FileShare.Read);
                    this.bearerToken = (String)formatter.Deserialize(stream);
                    stream.Close();
                }
                else
                {
                    this.getAuthenticationToken(true);
                }
            }

        }

        private async Task searchTweets(String search)
        {
            var client = new HttpClient();
            var searchUrl = string.Format("https://api.twitter.com/1.1/search/tweets.json?q={0}&count={1}&result_type={2}", search, tweetCount, queryParam);
            var uri = new Uri(searchUrl);

            client.DefaultRequestHeaders.Add("Authorization", string.Format("Bearer {0}", this.bearerToken));
            HttpResponseMessage response = await client.GetAsync(uri);

            if (response.StatusCode == HttpStatusCode.Unauthorized)
            {
                getAuthenticationToken(true);
                searchTweets(search);
                return;
            }
            else if (response.StatusCode == HttpStatusCode.RequestTimeout)
            {
                MessageBox.Show(this, "Timed Out", "Error");
            }
            else if ((int)response.StatusCode == 429)
            {
                MessageBox.Show(this, "Returned in API v1.1 when a request cannot be served due to the application’s rate limit having been exhausted for the resource. See Rate Limiting in API v1.1.", "Too Many Requests");
            }
            else if (response.StatusCode == HttpStatusCode.BadGateway)
            {
                MessageBox.Show(this, "Twitter is down or being upgraded.", "Bad Gateway");
            }
            else if (response.StatusCode == HttpStatusCode.ServiceUnavailable)
            {
                MessageBox.Show(this, "The Twitter servers are up, but overloaded with requests. Try again later.", "Service Unavailable");
            }
            else if (response.StatusCode == HttpStatusCode.NotAcceptable)
            {
                MessageBox.Show(this, "Returned by the Search API when an invalid format is specified in the request.", "Not Acceptable");
            }
            else if (response.StatusCode == HttpStatusCode.NotFound)
            {
                MessageBox.Show(this, "The URI requested is invalid or the resource requested, such as a user, does not exists. Also returned when the requested format is not supported by the requested method.", "Not Found");
            }

            string content = await response.Content.ReadAsStringAsync();
            parseTweets(content);
        }

        private void parseTweets(String json)
        {
            JObject stuff = JObject.Parse(json);
            JArray tweets = (JArray)stuff["statuses"];
            List<Tweet> items = new List<Tweet>();
            foreach (JObject tweet in tweets)
            {
                Tweet temp = new Tweet();
                temp.id = (String)tweet["id_str"];
                temp.profilePicPath = (String)tweet["user"]["profile_image_url"];
                temp.name = (String)tweet["user"]["name"];
                temp.userName = (String)tweet["user"]["screen_name"];
                temp.message = (String)tweet["text"];
                temp.Date = (String)tweet["created_at"];
                temp.retweets = (String)tweet["retweet_count"];
                temp.favorites = (String)tweet["favorite_count"];
                temp.verified = (bool)tweet["user"]["verified"];
                items.Add(temp);
            }
            this.tweetsList.ItemsSource = items;
        }

        private void searchButton_Click(object sender, RoutedEventArgs e)
        {
            if (searchTextBoxHasValue() && hasInternetConnectivity())
            {
                searchTweets(searchTextBox.Text);
            }
            else if (!searchTextBoxHasValue())
            {
                MessageBox.Show(this, "Enter keywords to search for.", "Oops");
            }
            else if (!hasInternetConnectivity())
            {
                MessageBox.Show(this, "No internet connection.", "Error");
            }
        }

        private void popularRadio_Checked(object sender, RoutedEventArgs e)
        {
            this.queryParam = "popular";
            restartSearchWithFilter();
        }

        private void recentRadio_Checked(object sender, RoutedEventArgs e)
        {
            this.queryParam = "recent";
            restartSearchWithFilter();
        }

        private void tweetsCountSlider_ValueChanged(object sender, RoutedPropertyChangedEventArgs<double> e)
        {
            this.tweetCount = (int) tweetsCountSlider.Value;
        }

        private void tweetsList_SelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            saveTweet(e);
        }

        private void saveTweet(System.Windows.Controls.SelectionChangedEventArgs e)
        {
            if (tweetsList.SelectedIndex > -1)
            {
                Microsoft.Win32.SaveFileDialog dlg = new Microsoft.Win32.SaveFileDialog();
                dlg.FileName = "Tweet"; // Default file name
                dlg.DefaultExt = ".png"; // Default file extension
                dlg.Filter = "Image files (.png)|*.png"; // Filter files by extension

                // Show save file dialog box
                Nullable<bool> result = dlg.ShowDialog();

                // Process save file dialog box results
                if (result == true)
                {
                    // Save document
                    ListViewItem listItemView = (ListViewItem)this.tweetsList.ItemContainerGenerator.ContainerFromItem(e.AddedItems[0]);
                    listItemView.Measure(new Size(listItemView.ActualWidth, listItemView.ActualHeight));
                    listItemView.Arrange(new Rect(new Size(listItemView.ActualWidth, listItemView.ActualHeight)));

                    RenderTargetBitmap bitmap = new RenderTargetBitmap((int)listItemView.ActualWidth, (int)listItemView.ActualHeight, 96, 96, PixelFormats.Pbgra32);
                    bitmap.Render(listItemView);

                    tweetsList.Items.Refresh();

                    using (Stream file = dlg.OpenFile())
                    {
                        PngBitmapEncoder encoder = new PngBitmapEncoder();
                        encoder.Frames.Add(BitmapFrame.Create(bitmap));
                        encoder.Save(file);
                    }
                }

                tweetsList.SelectedIndex = -1;
            }
        }

        private void MainWindow1_Loaded(object sender, RoutedEventArgs e)
        {
            centerNoTweetsLabel();
            this.searchTextBox.Focus();
        } 

        private void centerNoTweetsLabel()
        {
            double left = (this.tweetsList.ActualWidth * .50) - (this.noTweetsLabel.ActualWidth / 2);
            double bottom = (this.tweetsList.ActualHeight * .50) - (this.noTweetsLabel.ActualHeight / 2);
            this.noTweetsLabel.Margin = new Thickness(left, 0, 0, bottom);
        }

        private void searchTextBox_KeyDown(object sender, System.Windows.Input.KeyEventArgs e)
        {
            if (e.Key == System.Windows.Input.Key.Enter)
            {
                searchButton_Click(null, e);
            }
        }

        /////// Helper Methods ////////////////

        private bool hasInternetConnectivity()
        {
            try
            {
                Ping myPing = new Ping();
                String host = "google.com";
                byte[] buffer = new byte[32];
                int timeout = 1000;
                PingOptions pingOptions = new PingOptions();
                PingReply reply = myPing.Send(host, timeout, buffer, pingOptions);
                return (reply.Status == IPStatus.Success);
            }
            catch (Exception)
            {
                return false;
            }
        }

        private bool searchTextBoxHasValue()
        {
            return searchTextBox.Text.Length > 0;
        }

        private void restartSearchWithFilter()
        {
            if (searchTextBoxHasValue() && hasInternetConnectivity() && tweetsList.HasItems)
            {
                searchTweets(searchTextBox.Text);
            }
        }
    }
}
