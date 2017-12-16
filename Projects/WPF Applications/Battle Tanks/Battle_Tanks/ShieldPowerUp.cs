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
    class ShieldPowerUp : PowerUp
    {
        static BitmapImage bMap = null;
        public ShieldPowerUp()
        {
            UseImage("power_up_shield.png", bMap);
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
                    GameEngine.playerTank.pickUp = PickUpState.Shield;
                    makeInactive();
                }
            }
        }
    }
}
