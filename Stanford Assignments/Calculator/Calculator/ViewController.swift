//
//  ViewController.swift
//  Calculator
//
//  Created by Gregory Prosper on 6/24/15.
//  Copyright (c) 2015 Gregory Prosper. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var display: UILabel!
    @IBOutlet weak var historyLabel: UILabel!
    
    var userIsCurrentlyTyping = false
    var operandStack = Array<Double>()


    @IBAction func appendDigit(sender: UIButton) {
        let digit = sender.currentTitle!
        println("digit: \(digit)")
        
        if userIsCurrentlyTyping{
            display.text = display.text! + digit
        }
        else{
            userIsCurrentlyTyping = true
            display.text = digit
        }
        
    }
    
    @IBAction func operate(sender: UIButton) {
        let operation = sender.currentTitle!
        if userIsCurrentlyTyping{
            enter()
        }
        switch operation{
            case"×": performOperation(operation) { $0 * $1 }
            case"−": performOperation(operation) { $1 - $0 }
            case"+": performOperation(operation) { $0 + $1 }
            case"÷": performOperation(operation) { $1 / $0 }
            case"√": performOperation(operation) { sqrt($0) }
            case"sin": performOperation(operation) { sin($0) }
            case"cos": performOperation(operation) { cos($0) }
            case"π": performOperation(M_PI)
            case"C": clear()
            default: break
        }
        
    }
    
    @IBAction func backspace() {
        if !userIsCurrentlyTyping{
            return
        }
        
        if count(display.text!) > 0{
            display.text = dropLast(display.text!)
        }
        else{
            let alert = UIAlertController(title: "Error", message: "Nothing to Delete", preferredStyle: UIAlertControllerStyle.Alert)
            
            alert.addAction(UIAlertAction(title: "Dismiss", style: .Default, handler: nil))
            
            self.presentViewController(alert, animated: true, completion: nil)
        }
    }
    
    @IBAction func addDecimal() {
        if display.text!.rangeOfString(".") == nil{
            display.text = display.text! + "."
            userIsCurrentlyTyping = true
        }
    }
    
    @IBAction func changeSigns(){
        if userIsCurrentlyTyping{
            toggleSign()
        }
        else{
            toggleSign()
            operandStack.removeLast()
            enter()
        }
    }
    
    func toggleSign(){
        if display.text!.rangeOfString("-") == nil{
            display.text = "-" + display.text!
        }
        else{
            display.text = dropFirst(display.text!)
        }
    }
    
    func clear(){
        historyLabel.text = ""
        displayValue = nil
        operandStack.removeAll(keepCapacity: false)
    }
    
    private func performOperation(name: String, operation: (Double,Double) -> Double){
        if operandStack.count >= 2{
            let d1 = operandStack.removeLast()
            let d2 = operandStack.removeLast()
            historyLabel.text = "\(d1) \(name) \(d2)"
            displayValue = operation(d1, d2)
            enter()
        }
    }
    
    private func performOperation(name: String, operation: Double -> Double){
        if operandStack.count >= 1{
            let d1 = operandStack.removeLast()
            historyLabel.text = "\(name)(\(d1))"
            displayValue = operation(d1)
            enter()
        }
    }
    
    private func performOperation(constant: Double){
        displayValue = constant
        enter()
    }
    
    @IBAction func enter() {
        userIsCurrentlyTyping = false
        if let x = displayValue{
            operandStack.append(x)
        }
        println("\(operandStack)")
    }
    
    var displayValue: Double?{
        get{
            return NSNumberFormatter().numberFromString(display.text!)?.doubleValue as Double?
        }
        set{
            if newValue == nil{
                display.text = ""
            }
            else{
                display.text = "\(newValue!)"
            }
            userIsCurrentlyTyping = false
        }
    }
}

