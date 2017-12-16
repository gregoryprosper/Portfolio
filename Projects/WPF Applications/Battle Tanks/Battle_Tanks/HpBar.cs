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
    class HpBar : GameObject
    {
        public Tank sprite;
        public HpBar()
        {
            Rectangle baseElement = new Rectangle();
            baseElement.Fill = Brushes.Red;
            baseElement.Height = 10;
            baseElement.Width = 100;
            baseElement.Opacity = .25;

            element = baseElement;

            AddToGame();
        }

        public override void update()
        {
            X = sprite.X - ((100 - sprite.Hp) / 2);
            Y = sprite.Y - sprite.ScaledHeight;
            this.Width = sprite.Hp;
        }
    }
}
