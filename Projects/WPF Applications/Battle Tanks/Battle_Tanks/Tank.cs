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
    enum TankState{
        Clear,
        Hit
    }

    abstract class Tank : GameObject
    {
        protected int frameCount = 0;
        public TankState state = TankState.Clear;

        protected HpBar hpBar;
        public HpBar HpBar{
            get
            {
                return hpBar;
            }
            private set
            {
                this.hpBar = value;
            }
        }

        protected int hp;
        public int Hp {
            get
            {
                return hp;
            }
        }

        public Tank()
        {
            hp = 100;
            hpBar = new HpBar();
            hpBar.sprite = this;
        }

        public void SetInMiddle()
        {
            X = G.gameWidth / 2.0;
            Y = G.gameHeight - (ScaledHeight / 2.0) - 3.0;
        }

        protected void takeHit(int damage)
        {
            this.hp -= damage;

            if (Hp <= 0)
            {
                Explosion e = Explosion.getNextAvailable();
                e.X = X;
                e.Y = Y;
                makeInactive();
                if (this is EnemyTank) GameEngine.score += 5;
            }
        }

        protected double getCannonTipX()
        {
            double vectorX = Math.Cos((Math.PI / 180) * Angle);
            double vectorY = Math.Sin((Math.PI / 180) * Angle);
            double magnitude = Math.Sqrt(Math.Pow(vectorX, 2) + Math.Pow(vectorY, 2));
            double unitVectorX = Math.Cos((Math.PI / 180) * Angle) / magnitude;
            return unitVectorX * (ScaledWidth / 2);
        }

        protected double getCannonTipY()
        {
            double vectorX = Math.Cos((Math.PI / 180) * Angle);
            double vectorY = Math.Sin((Math.PI / 180) * Angle);
            double magnitude = Math.Sqrt(Math.Pow(vectorX, 2) + Math.Pow(vectorY, 2));
            double unitVectorY = Math.Sin((Math.PI / 180) * Angle) / magnitude;
            return unitVectorY * (Height / 2);
        }

        protected void moveForward()
        {
            if (X <= G.gameWidth && X >= 0 && Y >= 0 && Y <= G.gameHeight)
            {
                double vectorX = Math.Cos((Math.PI / 180) * Angle);
                double vectorY = Math.Sin((Math.PI / 180) * Angle);
                double magnitude = Math.Sqrt(Math.Pow(vectorX, 2) + Math.Pow(vectorY, 2));
                double unitVectorX = Math.Cos((Math.PI / 180) * Angle) / magnitude;
                double unitVectorY = Math.Sin((Math.PI / 180) * Angle) / magnitude;
                X += unitVectorX * 5;
                Y += unitVectorY * 5;
            }
            else
            {
                if (X <= 0)
                {
                    X = 0;
                    if (this is EnemyTank) Angle += 180;
                }
                if (X >= G.gameWidth)
                {
                    X = G.gameWidth;
                    if (this is EnemyTank) Angle += 180;
                }
                if (Y <= 0)
                {
                    Y = 0;
                    if (this is EnemyTank) Angle += 180;
                }
                if (Y >= G.gameHeight)
                {
                    Y = G.gameHeight;
                    if (this is EnemyTank) Angle += 180;
                }
            }
        }

        protected void moveBackward()
        {
            if (X <= G.gameWidth && X >= 0 && Y >= 0 && Y <= G.gameHeight)
            {
                double vectorX = Math.Cos((Math.PI / 180) * Angle);
                double vectorY = Math.Sin((Math.PI / 180) * Angle);
                double magnitude = Math.Sqrt(Math.Pow(vectorX, 2) + Math.Pow(vectorY, 2));
                double unitVectorX = Math.Cos((Math.PI / 180) * Angle) / magnitude;
                double unitVectorY = Math.Sin((Math.PI / 180) * Angle) / magnitude;
                X -= unitVectorX * 5;
                Y -= unitVectorY * 5;
            }
            else
            {
                if (X <= 0)
                {
                    X = 0;
                }
                if (X >= G.gameWidth)
                {
                    X = G.gameWidth;
                }
                if (Y <= 0)
                {
                    Y = 0;
                }
                if (Y >= G.gameHeight)
                {
                    Y = G.gameHeight;
                }
            }
        }

        protected void shoot(Tank tank)
        {
            frameCount = 0;
            Bullet bullet = Bullet.getNextAvailable();

            if (tank is PlayerTank)
            {
                bullet.origin = Origin.Player;
            }
            else
            {
                bullet.origin = Origin.Enemy;
            }

            if (bullet != null)
            {
                bullet.X = X + getCannonTipX();
                bullet.Y = Y + getCannonTipY();
                bullet.initializeSpeed(Angle);
                bullet.frameCount = 1000;
            }
        }
    }
}
