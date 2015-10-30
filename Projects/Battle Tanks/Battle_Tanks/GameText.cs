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
using System.Windows.Media;
using System.Windows.Shapes;

namespace BattleTanks
{
    public abstract class GameText : GameObject
    {

        public GameText()
        {
            TextBlock baseElement = new TextBlock();
            baseElement.Height = 10;
            baseElement.Width = 10;
            element = baseElement;
        }




        public Color FontColor
        {
            set
            {
                ((TextBlock)(element)).Foreground = new SolidColorBrush(value);

            }
         }

        public double FontSize
        {
            set
            {
                ((TextBlock)element).FontSize = value;
            }
            get
            {
                return ((TextBlock)element).FontSize;
            }
        }

        public string FontFamily
        {
            set
            {
                ((TextBlock)element).FontFamily = new FontFamily(value);
            }
        }

        public TextAlignment TextAlignment
        {
            set
            {
                ((TextBlock)element).TextAlignment = value;
            }
        }


        public string Text
        {
            get
            {
                return ((TextBlock)element).Text;
            }
            set
            {
                ((TextBlock)element).Text = value;
            }
        }



    }
}
