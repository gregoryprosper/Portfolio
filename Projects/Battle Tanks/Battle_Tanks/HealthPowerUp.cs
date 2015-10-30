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
using System.Windows.Media.Imaging;

namespace BattleTanks
{
    class HealthPowerUp : PowerUp
    {
        static BitmapImage bMap = null;
        public HealthPowerUp()
        {
            UseImage("health.png", bMap);
            Scale = 0.75;
            makeInactive();
            list.Add(this);
            AddToGame();
        }

        public override void update()
        {
            if (isActive)
            {
                if (G.checkCollision(this, GameEngine.playerTank))
                {
                    GameEngine.playerTank.pickUp = PickUpState.Health;
                    makeInactive();
                }
            }
        }
    }
}
