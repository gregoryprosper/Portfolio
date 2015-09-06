//
//  SecondViewController.swift
//  Scrambler
//
//  Created by Gregory Prosper on 6/27/15.
//  Copyright (c) 2015 Prosper Inc. All rights reserved.
//

import UIKit

class Unscrambler: UIViewController {

    @IBOutlet weak var keyField: UITextField!
    @IBOutlet weak var textView: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    override func touchesBegan(touches: Set<NSObject>, withEvent event: UIEvent) {
        if textView.isFirstResponder(){
            textView.resignFirstResponder()
        }
        
        if keyField.isFirstResponder(){
            keyField.resignFirstResponder()
        }
    }
}

