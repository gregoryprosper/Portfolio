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
using System.Media;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace BattleTanks
{
    class Background : GameObject
    {
        static BitmapImage bMap = null;

        public Background()
        {
            UseImage("backgroundTiles.jpg", bMap);

            X = G.gameWidth / 2.0;
            Y = G.gameHeight / 2.0;

            Scale = 2.0;
            AddToGame();
        }

    }
}
