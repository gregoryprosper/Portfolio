   M S B A M L   `   `   `  ���� F  BTwitFeedWPF, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null)clr-namespace:TwitFeedWPFTwitFeedWPF      TwitFeedWPF.MainWindow   W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     0
Convertersclr-namespace:TwitFeedWPF     -     �Name$  MainWindow1�� � Title$ TwitFeed��$	��600��$	��800�����	#FF58AEE8 � 
ResizeMode$ NoResize=� � 	Resources .   (TwitFeedWPF.BooleanToVisibilityConverter   BooleanToVisibility  twitter �� 	UriSource  verified  retweet  favorite �� 
TargetType �� Triggers P� Property P� Value	 �� Property
 �� Value �� 
TargetType��%"  &	        &	      &	 #     &	 B     &	 `     '��        �� $ Images/twitter.png?��� $ Images/verified.png?��� $ Images/retweet.png?��� $ Images/favorite.png?��� " �� P�  �@��
IsSelected . . ��� 	 � |�
 .  �� 	 � ��
 �� " ��.R��� .��� -    �Name$ mainGrid��.+��� -    ��Name$ searchTextBox��$��50��$��100,30,116,0q� �� TextWrapping$
 Wrap=�$	��Top=�$��36(��� -    ��Name$ searchButton��$��Search��$��	0,30,10,0q�$	��Top=�$��50��$��Right=�$	��101���� -    ��Name$ 
tweetsList��$��10,115,10,10q�$��Disabled=�
����������� .R�� $
��0,10q� � ColumnDefinitions �� $��50 ��� $��* � � RowDefinitions �� $��50 ��� $
��Auto ��� $
��Auto �.+��� $��0��$��0�� �� Orientation$ 
Horizontal=�.+��� $
��Fill=�$��50��$��50��}�.  ���� *
profilePic+�� $��1��$��0��$��5,0,0,0q�.+�� $
��Bold"� �� $	��300 ��� $
��Auto ��� $��75 ��� $
��Auto ��� $
��Auto �.+��� $��0��$ 
Horizontal=�.+��� $��20(�$��Franklin Gothic Medium)�$��CharacterEllipsis=�$
��Left=����� *name+�� $
��Fill=�$��20��$��20��}�.  $��5,0,0,0q�$
��Left=�$��Center=�#���  y��� *
verified+ �� 	Converter# [  �� $��1��$
��Fill=�$��20��$��20��}�.  $��20,0,0,0q�$
��Left=�$��Center=�#���  �� $��2��$
��Left=�$��Center=����� *
retweets+�� $��3��$
��Fill=�$��20��$��20��}�.  $��20,0,0,0q�$
��Left=�$��Center=�#���  �� $��4��$
��Left=�$��Center=����� *	favorites+�� $ Vertical=�.+��� $��16(����� *
userName+� $��0��$��1��$��2��$��	5,10,0,10q�.+���  ��Name$ messageTextBox��$��18(�$��Arial)�$
��Wrap=����� *	message+���� *Width+ �� ElementName$ 
tweetsList���� $��0��$��2��$��2��$��5,0,0,0q�.+��� $��12(�$��Calibri Light)����� *Date+�� $
��Fill=�$��	26,30,0,0q�$��50��$	��Top=�$
��Left=�$��50��#��[ 	� -    	�Name$ recentRadio��$��Recent��$
��Left=�$��
100,85,0,0q�$	��Top=�$��25��$��60�� d� 	IsChecked$
 TrueY� 	� 	GroupName$ 
sortRadios��	� -   $ popularRadio��$��Popular��$
��Left=�$��
165,85,0,0q�$	��Top=�$��25��$��69��$ 
sortRadios���� -    ��Name$ tweetsCountSlider��$��
0,85,116,0q�$	��Top=�$��Right=�$	��150�� � Maximum$ 50Q� � Minimum$ 10Q� � Value$ 20Q� � SmallChange$ 5Q� � LargeChange$ 10Q��� $��Tweets��$��Right=�$��
0,80,271,0q�$	��Top=�$��25����   �� ContentStringFormat$  {0:#}��$��
0,80,317,0q�$	��Top=�$��Right=�$��23������ $ tweetsCountSlider��! �� Path$! Value��� -	   " ��Name$" noTweetsLabel��$��	No Tweets��$��341,0,0,202q�# J� RenderTransformOrigin$# -1.321,0.722&�$
��Left=�$��Bottom=�$��66��$��26��$��22(�y��� *Items.IsEmpty+$ 
tweetsList��# [  