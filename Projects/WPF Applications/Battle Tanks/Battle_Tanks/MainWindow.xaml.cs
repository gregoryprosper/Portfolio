//************************************************
//
// (c) Copyright 2015 Dr. Thomas Fernandez
//
// All rights reserved.
//
//************************************************

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace BattleTanks
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        Canvas canvas = new Canvas();

        public MainWindow()
        {
            InitializeComponent();
            this.Loaded += MainWindow_Loaded;
        }

        void MainWindow_Loaded(object sender, RoutedEventArgs e)
        {
            WindowState = WindowState.Maximized;
            mainGrid.Height = Height-40;
            mainGrid.Width = Width;

            canvas.Height = mainGrid.Height;
            canvas.Width = mainGrid.Width;

            mainGrid.Children.Add(canvas);

            this.KeyDown += MainWindow_KeyDown;
            this.KeyUp += MainWindow_KeyUp;

            G.canvas = canvas;
            G.gameEngine.SetUpAllGameObjects(true);

        }

        void MainWindow_KeyUp(object sender, KeyEventArgs e)
        {
            int keyNum = (int)e.Key;
            G.gameEngine.keyPressed[keyNum] = false;
        }

        void MainWindow_KeyDown(object sender, KeyEventArgs e)
        {
            int keyNum=(int) e.Key;
            G.gameEngine.keyPressed[keyNum] = true;
        }



    }
}
