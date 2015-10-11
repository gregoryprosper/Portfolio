using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media;
using System.Windows.Media.Imaging;

namespace TwitFeedWPF
{
    class Tweet
    {
        public string id { get; set; }
        public string profilePicPath { get; set; }
        public BitmapImage profilePic
        {
            get
            {
                if (profilePicPath != null)
                {
                    return new BitmapImage(new Uri(profilePicPath, UriKind.Absolute));
                }
                else return null;
                
            }
        }
        public string name { get; set; }
        public string userName { get; set; }
        public string message { get; set; }
        public string retweets { get; set; }
        public string favorites { get; set; }
        public bool verified { get; set; }

        private string date;
        public string Date {
            get
            {
                return date;
            }
            set {
                date = value.Substring(0, 16);
            }

        }
    }
}
