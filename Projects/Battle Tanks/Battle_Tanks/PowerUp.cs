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

namespace BattleTanks
{
    abstract class PowerUp : GameObject
    {
        public static List<PowerUp> list = new List<PowerUp>();

        public static PowerUp getNextAvailable()
        {
            int index = G.randI(0, list.Count - 1);
            PowerUp pu = list[index];
            if (!pu.isActive)
            {
                return pu;
            }

            return null;
        }
    }
}
