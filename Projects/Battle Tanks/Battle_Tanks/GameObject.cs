﻿//************************************************
//
// (c) Copyright 2015 Dr. Thomas Fernandez
//
// All rights reserved.
//
//************************************************

using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Timers;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Threading;

namespace BattleTanks
{
    public abstract class GameObject
    {

        private FrameworkElement _element = null;

        public FrameworkElement element
        {
            get { return _element; }
            set 
            {
                _element = value;
                rotateTransform = new RotateTransform();
                rotateTransform.CenterX = _element.Width / 2.0;
                rotateTransform.CenterY = _element.Height / 2.0;
                scaleTransform = new ScaleTransform();
                scaleTransform.CenterX = _element.Width / 2.0;
                scaleTransform.CenterY = _element.Height / 2.0;
                translateTransform = new TranslateTransform();
         
                transformGroup = new TransformGroup();
                transformGroup.Children.Add(rotateTransform);
                transformGroup.Children.Add(scaleTransform);
                transformGroup.Children.Add(translateTransform);
                _element.RenderTransform = transformGroup;
         
                if (_canvas != null)
                {
                    _canvas.Children.Add(_element);
                }
                if (_effect != null)
                {
                    _canvas.Effect = _effect;
                }
            }
        }

        public void UseImage(string imageFileName, BitmapImage b)
        {
            if (b == null)
            {
                b = new BitmapImage();
                G.CheckForAlternateContentDir(imageFileName);

                b.BeginInit();
                b.UriSource = new Uri(G.ContentDir + imageFileName, UriKind.Relative);
                b.EndInit();
            }

            Image baseElement = new Image();
            baseElement.Source = b;
            baseElement.Stretch = Stretch.Fill;
            baseElement.Height = b.Height;
            baseElement.Width = b.Width;


            element = baseElement;
        }



        public void AddToGame()
        {
            canvas = G.canvas;
            G.gameEngine.addToDisplayList(this);
        }

        private double _Scale;

        public double Scale
        {
            get
            {
                //return (scaleTransform.ScaleX + scaleTransform.ScaleY) / 2.0;
                return _Scale;
            }
            set
            {
                _Scale = value;
                scaleTransform.ScaleX = value * (G.gameWidth / 1920.0);
                scaleTransform.ScaleY = value * (G.gameHeight / 1080.0);
            }
        }
        public double Angle
        {
            get
            {
                return rotateTransform.Angle;
            }
            set
            {
                rotateTransform.Angle = value;
            }
        }


        public double Height
        {
            get
            {
                 return element.Height;
            }
            set
            {
                element.Height = value;
                rotateTransform.CenterX = _element.Width / 2.0;
                rotateTransform.CenterY = _element.Height / 2.0;
                scaleTransform.CenterX = _element.Width / 2.0;
                scaleTransform.CenterY = _element.Height / 2.0;
            }
        }

        public double Width
        {
            get
            {
                return element.Width;
            }
            set
            {
                element.Width = value <= 0 ? 0 : value;
                rotateTransform.CenterX = _element.Width / 2.0;
                rotateTransform.CenterY = _element.Height / 2.0;
                scaleTransform.CenterX = _element.Width / 2.0;
                scaleTransform.CenterY = _element.Height / 2.0;
            }
        }

        public double ScaledHeight
        {
            get
            {
                return element.Height * scaleTransform.ScaleY;
            }
        }

        public double ScaledWidth
        {
            get
            {
                return element.Width * scaleTransform.ScaleX;
            }
        }
 
        public double X
        {
            get
            {
                return translateTransform.X + Width / 2.0;
                //return translateTransform.X;
            }
            set
            {
                translateTransform.X = value - Width / 2.0;
            }

        }
        public double Y
        {
            get
            {
                return translateTransform.Y + Height / 2.0;
                //return translateTransform.Y;
            }
            set
            {
                translateTransform.Y = value - Height / 2.0;
            }
        }


        public void makeInactive()
        {
            Y = -10000.0;
            X = -10000.0;

            dX = 0.0;
            dY = 0.0;
        }

        public bool isActive
        {
            get
            {
                return Y > -5000.0;
            }
        }


        public double _dX;
        public double _dY;


        public double dX
        {
            get
            {
                return _dX / (1920.0 / G.gameWidth);
            }
            set
            {
                _dX = value * (1920.0 / G.gameWidth);
            }
        }

        public double dY
        {
            get
            {
                return _dY / (1080.0 / G.gameHeight); 
            }
            set
            {
                _dY = value * (1080.0 / G.gameHeight);
            }
        }

        public RotateTransform rotateTransform
        {
            get;
            set;
        }

        public ScaleTransform scaleTransform
        {
            get;
            set;
        }

        public TranslateTransform translateTransform
        {
            get;
            set;
        }




        public TransformGroup transformGroup
        {
            get;
            set;
        }

        public Effect effect
        {
            get
            {
                return _effect;
            }
            set
            {
                _effect = value;
                if(_element!=null)
                {
                    _element.Effect = _effect;
                }
            }
        }

        Effect _effect=null;

        public Canvas canvas
        {
            get
            {
                return _canvas;
            }
            set
            {
                _canvas=value;
                if (_element != null)
                {
                    _canvas.Children.Add(_element);
                }
            }
        }

        private Canvas _canvas = null;


 

        //private DispatcherTimer _timer = null;

        virtual public void update()
        {

        }

    }
}
