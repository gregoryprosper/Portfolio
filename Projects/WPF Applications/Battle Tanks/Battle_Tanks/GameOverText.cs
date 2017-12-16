//************************************************
//
// (c) Copyright 2015 Gregory Prosper
//
// All rights reserved.
//
//************************************************

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows.Media;

namespace BattleTanks
{
    class GameOverText : GameText
    {
        public GameOverText()
        {
            FontFamily = "Arial Black";
            Height = 500.0;
            Width = 3000.0;
            TextAlignment = TextAlignment.Center;
            FontSize = 100;

            Y = G.gameHeight * 0.5;
            X = G.gameWidth * 0.5;

            dX = 0.0;
            dY = 0.0;

            Scale = 0.75;

            ((TextBlock)(element)).Foreground = new SolidColorBrush(Color.FromRgb(255, 0, 0));

            AddToGame();
        }
    }
}
