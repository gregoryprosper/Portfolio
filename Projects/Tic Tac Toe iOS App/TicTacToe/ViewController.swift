//
//  ViewController.swift
//  TicTacToe
//
//  Created by Gregory Prosper on 12/11/15.
//  Copyright © 2015 Prosper Inc. All rights reserved.
//

import UIKit

enum Turn{
    case X
    case O
}

class ViewController: UIViewController {
    
    var playerTurn:Turn = Turn.X
    var gameOver:Bool = false
    
    @IBOutlet var blocks: [UIButton]!
    @IBOutlet weak var gameLabel: UILabel!
    @IBOutlet weak var resetButton: UIButton!

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        initializeBoard()
    }

    func initializeBoard(){
        for block in blocks{
            block.setImage(nil, forState:UIControlState.Normal)
            block.setTitle("", forState: UIControlState.Normal)
        }
        gameLabel.text = "Player 1 Turn"
        gameOver = false
        playerTurn = Turn.X
        resetButton.hidden = true;
    }
    
    @IBAction func resetGame(sender: UIButton) {
        initializeBoard()
    }
    
    @IBAction func playerAction(block: UIButton) {
        guard !gameOver else {
            return
        }
        
        addXorO(block.tag)
        
        let winner = checkForWinner()
        let tie = checkForTie()
        
        if winner.hasWinner {
            gameOver = true
            resetButton.hidden = false;
            
            if winner.winner == "X"{
                gameLabel.text = "Player 1 Wins"
            }
            else if winner.winner == "O"{
                gameLabel.text = "Player 2 Wins"
            }
        }
        else if tie{
            gameOver = true
            resetButton.hidden = false
            gameLabel.text = "Tie!"
        }
    }
    
    func addXorO(location:Int){
        let block = getBlockAtLocation(location)
        
        if playerTurn == Turn.X {
            block?.setImage(UIImage(named: "cross.png"),forState: UIControlState.Normal)
            block?.setTitle("X", forState: UIControlState.Normal)
            playerTurn = Turn.O
            gameLabel.text = "Player 2 Turn"
        }
        else{
            block?.setImage(UIImage(named: "nought.png"),forState: UIControlState.Normal)
            block?.setTitle("O", forState: UIControlState.Normal)
            playerTurn = Turn.X
            gameLabel.text = "Player 1 Turn"
        }
        
    }
    
    func getBlockAtLocation(location:Int) -> UIButton?{
        for block in blocks{
            if block.tag == location{
                return block
            }
        }
        return nil
    }
    
    func checkForWinner() -> (hasWinner: Bool, winner:String?) {
        let firstRowMatch = checkRowForMatch(1,second: 2,third: 3)
        let secondRowMatch = checkRowForMatch(4,second: 5,third: 6)
        let thirdRowMatch = checkRowForMatch(7,second: 8,third: 9)
        let firstColumnMatch = checkRowForMatch(1,second: 4,third: 7)
        let secondColumnMatch = checkRowForMatch(2,second: 5,third: 8)
        let thirdColumnMatch = checkRowForMatch(3,second: 6,third: 9)
        let diagonalToRightMatch = checkRowForMatch(1, second: 5, third: 9)
        let diagonalToLeftMatch = checkRowForMatch(3, second: 5, third: 7)
        
        if firstRowMatch{
            return (true, getBlockAtLocation(1)?.titleForState(UIControlState.Normal))
        }
        else if secondRowMatch{
            return (true, getBlockAtLocation(4)?.titleForState(UIControlState.Normal))
        }
        else if thirdRowMatch{
            return (true, getBlockAtLocation(7)?.titleForState(UIControlState.Normal))
        }
        else if firstColumnMatch{
            return (true, getBlockAtLocation(1)?.titleForState(UIControlState.Normal))
        }
        else if secondColumnMatch{
            return (true, getBlockAtLocation(2)?.titleForState(UIControlState.Normal))
        }
        else if thirdColumnMatch{
            return (true, getBlockAtLocation(3)?.titleForState(UIControlState.Normal))
        }
        else if diagonalToRightMatch{
            return (true, getBlockAtLocation(1)?.titleForState(UIControlState.Normal))
        }
        else if diagonalToLeftMatch{
            return (true, getBlockAtLocation(3)?.titleForState(UIControlState.Normal))
        }
        else{
            return (false, nil)
        }
    }
    
    func checkRowForMatch(first:Int, second:Int, third:Int) -> Bool {
        let firstBlockText = getBlockAtLocation(first)?.titleForState(UIControlState.Normal)
        let secondBlockText = getBlockAtLocation(second)?.titleForState(UIControlState.Normal)
        let thirdBlockText = getBlockAtLocation(third)?.titleForState(UIControlState.Normal)
        
        guard firstBlockText?.isEmpty == false else{
            return false
        }
        
        guard secondBlockText?.isEmpty == false else{
            return false
        }
        
        guard thirdBlockText?.isEmpty == false else{
            return false
        }
        
        if firstBlockText == secondBlockText && firstBlockText == thirdBlockText {
                return true
        }
        else{
            return false
        }
    }
    
    func checkForTie() -> Bool{
        var tie:Bool = true
        for block in blocks{
            if block.titleForState(UIControlState.Normal) != "X" && block.titleForState(UIControlState.Normal) != "O"{
                tie = false;
            }
        }
        return tie
    }

}

