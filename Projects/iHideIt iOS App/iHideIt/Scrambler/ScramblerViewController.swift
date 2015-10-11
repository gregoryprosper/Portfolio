//
//  FirstViewController.swift
//  Scrambler
//
//  Created by Gregory Prosper on 6/27/15.
//  Copyright (c) 2015 Prosper Inc. All rights reserved.
//

import UIKit
import iAd

class Scrambler: UIViewController, ADBannerViewDelegate {

    @IBOutlet weak var textView: UITextView!
    @IBOutlet weak var keyField: UITextField!
    
    var bannerView: ADBannerView?
    
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
    
    func clearBanner(){
        bannerView?.removeFromSuperview()
        bannerView?.delegate = nil
        bannerView = nil
    }
    
    func setUpPasswordField(){
        self.keyField.secureTextEntry = NSUserDefaults.standardUserDefaults().boolForKey("hidePassword")
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        setUpPasswordField()
        setUpAddBanner()
    }
    
    override func viewWillDisappear(animated: Bool) {
        super.viewWillDisappear(animated)
        if NSUserDefaults.standardUserDefaults().boolForKey("clearPassword"){
           keyField.text = ""
        }
        clearBanner()
    }

    override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
        if textView.isFirstResponder(){
            textView.resignFirstResponder()
        }
        
        if keyField.isFirstResponder(){
            keyField.resignFirstResponder()
        }
        
    }
    
    @IBAction func leftSwipeDetected(sender: UISwipeGestureRecognizer) {
        let currentIndex = self.tabBarController?.selectedIndex
        self.tabBarController?.selectedIndex = currentIndex! + 1
    }
    
    override func shouldPerformSegueWithIdentifier(identifier: String, sender: AnyObject?) -> Bool {
        if checkFieldsEmpty(){
            return true
        }
        else{
            let alert = UIAlertController(title: "Oops", message: "Fill all fields.", preferredStyle: .Alert)
            let action = UIAlertAction(title: "Dismiss", style: UIAlertActionStyle.Cancel, handler: nil)
            alert.addAction(action)
            presentViewController(alert, animated: true, completion: nil)
            return false
        }
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let dc: ScrambledMessageViewController = (segue.destinationViewController as! UINavigationController).topViewController as! ScrambledMessageViewController
        
        dc.scrambledMessage = ScramblerBrain.encryptString(textView.text, key: keyField.text!)
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

