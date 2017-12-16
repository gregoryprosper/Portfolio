using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BlockBuilder
{
    public partial class BlockBuilder : Form
    {
        List<Block> blocks = new List<Block>();
        Random rand = new Random();
        Block currentBlock;
        bool mousePressedDown = false;

        public BlockBuilder()
        {
            InitializeComponent();
            colorPanel.BackColor = Color.Black;
            trackBar.Value = Block.size;
        }

        private void mainPanel_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;
            foreach (Block block in blocks){
                using(Brush brush = new SolidBrush(block.Color)){
                    g.FillRectangle(brush, block.Bounds);
                }
                if (currentBlock == block){
                    using(Pen pen = new Pen(Color.Yellow,2.0f)){
                        g.DrawRectangle(pen, block.Bounds.X + block.Bounds.Width / 4, block.Bounds.Y + block.Bounds.Height / 4, block.Bounds.Width - block.Bounds.Width / 2, block.Bounds.Height - block.Bounds.Height / 2);
                    }
                }
            }
        }

        private Block addBlock(int x, int y){
            Block newBlock = new Block(x, y, colorPanel.BackColor);
            blocks.Add(newBlock);
            return newBlock;
        }

        private void colorChangeButton_Click(object sender, EventArgs e)
        {
            colorPanel.BackColor = Color.FromArgb(rand.Next(256), rand.Next(256), rand.Next(256));
        }

        private void Form1_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar == 'c')
            {
                colorPanel.BackColor = Color.FromArgb(rand.Next(256), rand.Next(256), rand.Next(256));
            }

            if (currentBlock != null){
                switch(e.KeyChar){
                    case 'e':
                        currentBlock.move(0, -1, mainPanel.Bounds);
                        break;
                    case 'd':
                        currentBlock.move(0, 1, mainPanel.Bounds);
                        break;
                    case 's':
                        currentBlock.move(-1, 0, mainPanel.Bounds);
                        break;
                    case 'f':
                        currentBlock.move(1, 0, mainPanel.Bounds);
                        break;
                    default:
                        break;
                }
                mainPanel.Invalidate();
            }
        }

        protected override bool ProcessCmdKey(ref Message msg, Keys keyData)
        {
            if (keyData == Keys.Return)
            {
                currentBlock = null;
                mainPanel.Invalidate();
                return true;
            }
            else if (keyData == Keys.Space){
                currentBlock = null;
                mainPanel.Invalidate();
                return true;
            }
            else if (keyData == Keys.Back)
            {
                if (currentBlock != null) {
                    blocks.Remove(currentBlock);
                    mainPanel.Invalidate();
                    return true;
                }
            }
            else if (keyData == (Keys.Control | Keys.Z)){
                if (blocks.Count > 0){
                    blocks.RemoveAt(blocks.Count - 1);
                    currentBlock = null;
                    mainPanel.Invalidate();
                }
                return true;
            }
            return base.ProcessCmdKey(ref msg, keyData);
        }

        private void colorPanel_Click(object sender, EventArgs e)
        {
            if (colorDialog1.ShowDialog() == DialogResult.OK)
            {
                colorPanel.BackColor = colorDialog1.Color;
            }
        }

        private void mainPanel_MouseClick(object sender, MouseEventArgs e)
        {
            bool found = false;
            foreach (Block block in blocks){
                if (block.containsPoint(e.Location)){
                    currentBlock = block;
                    found = true;
                    mainPanel.Invalidate();
                }
            }

            if (found == false){
                currentBlock = null;
                mainPanel.Invalidate();
            }
        }

        private void clearButton_Click(object sender, EventArgs e)
        {
            blocks.Clear();
            currentBlock = null;
            mainPanel.Invalidate();
        }

        private void mainPanel_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            currentBlock = addBlock(e.X - Block.size / 2, e.Y - Block.size / 2);
            mainPanel.Invalidate();
        }

        private void saveButton_Click(object sender, EventArgs e)
        {
            int width = mainPanel.Size.Width;
            int height = mainPanel.Size.Height;

            using (Bitmap bmp = new Bitmap(width, height))
            {
                mainPanel.DrawToBitmap(bmp, new Rectangle(0, 0, width, height));
                string strPath = Environment.GetFolderPath(Environment.SpecialFolder.DesktopDirectory);
                string path = strPath + "\\BlockBuilder.jpeg";
                bmp.Save(path, ImageFormat.Jpeg);
            }
        }

        private void mainPanel_MouseDown(object sender, MouseEventArgs e)
        {
            mousePressedDown = true;
        }

        private void mainPanel_MouseMove(object sender, MouseEventArgs e)
        {
            if (mousePressedDown){
                addBlock(e.X - Block.size / 2, e.Y - Block.size / 2);
                mainPanel.Invalidate(new Rectangle(e.X - Block.size / 2, e.Y - Block.size / 2, Block.size, Block.size));
            }
        }

        private void mainPanel_MouseUp(object sender, MouseEventArgs e)
        {
            mousePressedDown = false;
        }

        private void trackBar_Scroll(object sender, EventArgs e)
        {
            Block.size = trackBar.Value;
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Stream saveStream;
            IFormatter formatter = new BinaryFormatter();

            if (saveFileDialog1.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    if ((saveStream = saveFileDialog1.OpenFile()) != null)
                    {
                        using (saveStream)
                        {
                            formatter.Serialize(saveStream, this.blocks);
                        }
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Error could not save file: " + ex.Message);
                }
            }
        }

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Stream openStream;
            IFormatter formatter = new BinaryFormatter();

            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    if ((openStream = openFileDialog1.OpenFile()) != null)
                    {
                        using (openStream)
                        {
                            List<Block> fileObject = formatter.Deserialize(openStream) as List<Block>;
                            if (fileObject != null){
                                this.blocks = fileObject;
                                this.mainPanel.Invalidate();
                            }
                            else
                            {
                                MessageBox.Show("Invalid File!");
                            }
                        }
                    }
                }
                catch (Exception ex){
                    MessageBox.Show("Error could not open file: " + ex.Message);
                }
            }
        }
    }
}
