//
//  SettingsTableTableViewController.swift
//  iHideIt
//
//  Created by Gregory Prosper on 8/4/15.
//  Copyright (c) 2015 Prosper Inc. All rights reserved.
//

import UIKit

class SettingsTableTableViewController: UITableViewController {

    @IBOutlet weak var hidePasswordSwitch: UISwitch!
    @IBOutlet weak var clearPasswordSwitch: UISwitch!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        refreshSwitches()
    }
    
    func refreshSwitches(){
        hidePasswordSwitch.on = NSUserDefaults.standardUserDefaults().boolForKey("hidePassword")
        clearPasswordSwitch.on = NSUserDefaults.standardUserDefaults().boolForKey("clearPassword")
    }
    
    @IBAction func toggleShowPassword(sender: UISwitch) {
        NSUserDefaults.standardUserDefaults().setBool(sender.on, forKey: "hidePassword")
        NSUserDefaults.standardUserDefaults().synchronize()
    }
    
    
    @IBAction func toggleClearPassword(sender: UISwitch) {
        NSUserDefaults.standardUserDefaults().setBool(sender.on, forKey: "clearPassword")
        NSUserDefaults.standardUserDefaults().synchronize()
    }
}
