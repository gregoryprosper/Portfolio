//
//  SecondViewController.swift
//  Scrambler
//
//  Created by Gregory Prosper on 6/27/15.
//  Copyright (c) 2015 Prosper Inc. All rights reserved.
//

import UIKit
import iAd

class Unscrambler: UIViewController, ADBannerViewDelegate {

    @IBOutlet weak var keyField: UITextField!
    @IBOutlet weak var textView: UITextView!
    
    var bannerView: ADBannerView?
    
    override func viewWillDisappear(animated: Bool) {
        super.viewWillDisappear(animated)
        if NSUserDefaults.standardUserDefaults().boolForKey("clearPassword"){
            keyField.text = ""
        }
        clearBanner()
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        setUpPasswordField()
        setUpAddBanner()
    }
    
    
    func clearBanner(){
        bannerView?.removeFromSuperview()
        bannerView?.delegate = nil
        bannerView = nil
    }
    
    func setUpPasswordField(){
        self.keyField.secureTextEntry = NSUserDefaults.standardUserDefaults().boolForKey("hidePassword")
    }

    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        if textView.isFirstResponder(){
            textView.resignFirstResponder()
        }
        
        if keyField.isFirstResponder(){
            keyField.resignFirstResponder()
        }
    }
    
    @IBAction func rightSwipeDetected(sender: UISwipeGestureRecognizer) {
        let currentIndex = self.tabBarController?.selectedIndex
        self.tabBarController?.selectedIndex = currentIndex! - 1
    }
    
    @IBAction func leftSwipeDetected(sender: UISwipeGestureRecognizer) {
        let currentIndex = self.tabBarController?.selectedIndex
        self.tabBarController?.selectedIndex = currentIndex! + 1
    }
    
    func setUpAddBanner(){
        bannerView = ADBannerView(adType: .Banner)
        bannerView!.translatesAutoresizingMaskIntoConstraints = false
        bannerView!.delegate = self
        bannerView!.hidden = true
        view.addSubview(bannerView!)
        
        let lhConstraint = NSLayoutConstraint(item: bannerView!, attribute: NSLayoutAttribute.Leading, relatedBy: NSLayoutRelation.Equal, toItem: view, attribute: NSLayoutAttribute.Leading, multiplier: 1, constant: 0)
        
        let rhConstraint = NSLayoutConstraint(item: bannerView!, attribute: NSLayoutAttribute.Trailing, relatedBy: NSLayoutRelation.Equal, toItem: view, attribute: NSLayoutAttribute.Trailing, multiplier: 1, constant: 0)
        
        let btConstraint = NSLayoutConstraint(item: bannerView!, attribute: NSLayoutAttribute.Bottom, relatedBy: NSLayoutRelation.Equal, toItem: view, attribute: NSLayoutAttribute.Bottom, multiplier: 1, constant: -50)
        
        view.addConstraints([lhConstraint,rhConstraint,btConstraint])
    }
    
    override func shouldPerformSegueWithIdentifier(identifier: String, sender: AnyObject?) -> Bool {
        if checkFieldsEmpty(){
            if let unscrambledMessage = ScramblerBrain.decryptData(textView.text, key: keyField.text!){
                if unscrambledMessage == ""{
                    showInvalidAlert()
                    return false
                }
                else{
                    return true
                }
            }
            else{
                showInvalidAlert()
                return false
            }
        }
        else{
            let alert = UIAlertController(title: "Oops", message: "Fill all fields.", preferredStyle: .Alert)
            let action = UIAlertAction(title: "Dismiss", style: UIAlertActionStyle.Cancel, handler: nil)
            alert.addAction(action)
            presentViewController(alert, animated: true, completion: nil)
            return false
        }
    }
    
    func showInvalidAlert(){
        let alert = UIAlertController(title: "Oops", message: "Invalid Input/password", preferredStyle: .Alert)
        let action = UIAlertAction(title: "Dismiss", style: UIAlertActionStyle.Cancel, handler: nil)
        alert.addAction(action)
        presentViewController(alert, animated: true, completion: nil)
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let dc: UnscrambledMessageViewController = (segue.destinationViewController as! UINavigationController).topViewController as! UnscrambledMessageViewController
        dc.unscrambledMessage = ScramblerBrain.decryptData(textView.text, key: keyField.text!)
    }
    
    private func checkFieldsEmpty() -> Bool{
        return (textView.hasText() && keyField.hasText())
    }
    
    func bannerViewDidLoadAd(banner: ADBannerView!) {
        print("success")
        bannerView?.hidden = false
    }
    
    func bannerView(banner: ADBannerView!, didFailToReceiveAdWithError error: NSError!) {
        print(error.description)
        bannerView?.hidden = true
    }
    
}

