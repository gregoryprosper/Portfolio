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
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Threading;

namespace BattleTanks
{
    class GameEngine
    {
        DispatcherTimer timer = new DispatcherTimer();
        List<GameObject> displayList = new List<GameObject>();
        private MediaPlayer backgroundMusic = null;

        public bool[] keyPressed = new bool[256];

        public static double score = 0.0;
        public static bool gameNotOver = true;
        int numberOfEnemies = 1;
        int round = 0;

        public static ScoreText scoreText;
        public static HiScoreText hiScoreText;
        public static RoundText roundText;
        public static GameOverText gameOverText;
        private Background background1;

        public static PlayerTank playerTank;

        public GameEngine()
        {
            G.SetupSound(ref backgroundMusic, "backgroundMusic.mp3");
            backgroundMusic.MediaEnded += backgroundMusic_MediaEnded;
            backgroundMusic.Volume = 0.3;

            double msPerFrame = 10.0;
            timer.Interval = new TimeSpan(0, 0, 0, 0, (int)msPerFrame);
            
            timer.Start();
            timer.Tick += timer_Tick;
        }

        void backgroundMusic_MediaEnded(object sender, EventArgs e)
        {
            backgroundMusic.Position = TimeSpan.Zero;
            backgroundMusic.Play();
        }

        public void addToDisplayList(GameObject obj)
        {
            displayList.Add(obj);
        }

        public void clearGame()
        {
            double msPerFrame = 10;
            msPerFrame *= 1920.0 / G.gameWidth;
            timer.Interval = new TimeSpan(0, 0, 0, 0, (int)msPerFrame);

            displayList.Clear();
            G.canvas.Children.Clear();
            Bullet.list.Clear();
            Explosion.list.Clear();
            EnemyTank.list.Clear();
            PowerUp.list.Clear();
            Bullet.list.Clear();

        }

        public void RestartGame()
        {
            round = 0;
            score = 0.0;
            numberOfEnemies = 1;
            gameOverText = null;
            SetUpAllGameObjects(true);
        }

        public void SetUpAllGameObjects(bool isFirstTime=false)
        {
            backgroundMusic.Play();
            round++;
            gameNotOver = true;
            clearGame();
            ChangeNumberOfEnemies();

            //add objects to the game 
            if (isFirstTime)
            {
                background1 = new Background();
            }
            else
            {
                background1.AddToGame();
            }

            if (isFirstTime)
            {
                GameEngine.hiScoreText = new HiScoreText();
            }
            else
            {
                GameEngine.hiScoreText.AddToGame();
            }

            for (int i = 0; i < numberOfEnemies; i++)
            {
                EnemyTank enemy = new EnemyTank();
            }

            for (int i = 0; i < 3; i++)
            {
                ShieldPowerUp p = new ShieldPowerUp();
                HealthPowerUp h = new HealthPowerUp();
            }

            for (int i = 0; i < 1000; i++)
            {
                Bullet b = new Bullet();
            }

            if (isFirstTime)
            {
                playerTank = new PlayerTank();
            }
            else
            {
                playerTank.AddToGame();
                playerTank.HpBar.AddToGame();
                playerTank.shield.AddToGame();
                if (!playerTank.isActive) playerTank.SetInMiddle();
            }

            for (int i = 0; i < 100; i++)
            {
                Explosion obj = new Explosion();
            }

            GameEngine.scoreText = new ScoreText();
            GameEngine.roundText = new RoundText();
            GameEngine.roundText.Text = "Round " + round.ToString();
        }

        private void ChangeNumberOfEnemies()
        {
            if (numberOfEnemies > 150)
            {
                numberOfEnemies += G.randI(8, 10);
            }
            else if (numberOfEnemies > 70)
            {
                numberOfEnemies += G.randI(6, 8);
            }
            else if (numberOfEnemies > 25)
            {
                numberOfEnemies += G.randI(4, 6);
            }
            else
            {
                numberOfEnemies += G.randI(2, 4);
            }
        }


        void timer_Tick(object sender, EventArgs e)
        {
            //Quit Q
            if (G.isKeyPressed(Key.Q)) Quit();

            //Clear High Scores -+
            if ((G.isKeyPressed(Key.OemMinus)) && (G.isKeyPressed(Key.OemPlus))) hiScoreText.ClearHighScores();

            if (!gameNotOver)
            {
                if (gameOverText == null)
                {
                    backgroundMusic.Stop();
                    hiScoreText.addNewScore(GameEngine.score);
                    gameOverText = new GameOverText();
                    gameOverText.Text = "Game Over Press Esc to Try Again";
                }
                if (G.isKeyPressed(Key.Escape)) RestartGame();
            }

            if (G.chance(0.0005 * round))
            {
                PowerUp p = PowerUp.getNextAvailable();
                if (p != null){
                    p.X = G.randD(p.ScaledWidth / 2.0, G.gameWidth - p.ScaledWidth / 2.0);
                    p.Y = G.randD(p.ScaledHeight / 2.0, G.gameHeight - p.ScaledHeight / 2.0);
                }
            }

            if (allEnemiesDestroyed())
            {
                SetUpAllGameObjects();
            }

            foreach (GameObject obj in displayList)
            {
                obj.update();
            }
        }

        private bool allEnemiesDestroyed()
        {
            foreach (EnemyTank e in EnemyTank.list){
                if(e.isActive){
                   return false;
                }
            }
            return true;
        }

        private void Quit()
        {
            Application.Current.Shutdown();
        }

    }
}
