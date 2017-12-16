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
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Threading;

namespace BattleTanks
{
    enum PickUpState { None, Health, Shield}

    class PlayerTank : Tank
    {
        static BitmapImage bMap = null;
        private DispatcherTimer shieldTimer = new DispatcherTimer();
        public PickUpState pickUp;
        public PlayerShield shield;
        private MediaPlayer shieldSound = null;
        private MediaPlayer healthPickupSound = null;

        public PlayerTank()
        {
            UseImage("playerTank.png", bMap);
            Scale = 0.75;

            G.SetupSound(ref healthPickupSound, "healthPickup.mp3");
            G.SetupSound(ref shieldSound, "shield.mp3");
            shieldSound.MediaEnded += shieldSound_MediaEnded;

            shield = new PlayerShield(this);
            shieldTimer.Tick += timer_Tick;
            shieldTimer.Interval = TimeSpan.FromSeconds(20);

            Angle = -90;
            SetInMiddle();

            AddToGame();
        }

        void shieldSound_MediaEnded(object sender, EventArgs e)
        {
            shieldSound.Position = TimeSpan.Zero;
            shieldSound.Play();
        }

        private void timer_Tick(object sender, EventArgs e)
        {
            shieldTimer.Stop();
            shieldSound.Stop();
            shield.on = false;
        }

        public override void update()
        {
            if (isActive)
            {
                frameCount++;
                if (G.isKeyPressed(Key.Up))
                {
                    moveForward();
                }
                if (G.isKeyPressed(Key.Down))
                {
                    moveBackward();
                }
                if (G.isKeyPressed(Key.Right))
                {
                    Angle += 2;
                }
                if (G.isKeyPressed(Key.Left))
                {
                    Angle -= 2;
                }
                if (G.isKeyPressed(Key.Space) && (frameCount > 6))
                {
                    shoot(this);
                }

                if (state == TankState.Hit)
                {
                    takeHit(5);
                    state = TankState.Clear;
                    if (Hp <= 0) GameEngine.gameNotOver = false;
                }
                if (pickUp == PickUpState.Health)
                {
                    healthPickupSound.Stop();
                    healthPickupSound.Play();
                    pickUp = PickUpState.None;
                    hp = 100;
                }
                if (pickUp == PickUpState.Shield)
                {
                    pickUp = PickUpState.None;
                    shield.on = true;
                    shieldTimer.Stop();
                    shieldTimer.Start();
                    shieldSound.Play();
                }
            }
        }
    }
}
