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
using System.Threading.Tasks;
using System.Windows.Media;
using System.Windows.Shapes;

namespace BattleTanks
{
    class PlayerShield : GameObject
    {
        public PlayerTank sprite;
        public bool on = false;

        public PlayerShield(PlayerTank sprite)
        {
            Ellipse baseElement = new Ellipse();
            baseElement.StrokeThickness = 5;
            baseElement.Stroke = Brushes.LightBlue;
            baseElement.Height = sprite.ScaledHeight + 150;
            baseElement.Width = sprite.ScaledHeight + 150;
            baseElement.Opacity = .25;

            element = baseElement;

            this.sprite = sprite;

            AddToGame();
        }

        public override void update()
        {
            if (on)
            {
                X = sprite.X;
                Y = sprite.Y;
            }
            else
            {
                makeInactive();
            }
        }
    }
}
