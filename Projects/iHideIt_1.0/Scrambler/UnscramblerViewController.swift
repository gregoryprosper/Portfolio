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
    
    let banner: ADBannerView? = ADBannerView(adType: ADAdType.Banner) //Create banner
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        self.canDisplayBannerAds = true
        banner!.delegate = self;
    }

    override func viewDidAppear(animated: Bool) {
        if self.canDisplayBannerAds == true{
            let timer = NSTimer.scheduledTimerWithTimeInterval(10.0, target: self, selector: Selector("hideBanner"), userInfo: nil, repeats: false)
        }
    }
    
    func hideBanner(){
        println("hideBanner")
        self.canDisplayBannerAds = false
    }
    
    override func viewWillDisappear(animated: Bool) {
        keyField.text = ""
    }

    override func touchesBegan(touches: Set<NSObject>, withEvent event: UIEvent) {
        if textView.isFirstResponder(){
            textView.resignFirstResponder()
        }
        
        if keyField.isFirstResponder(){
            keyField.resignFirstResponder()
        }
    }
    
    override func shouldPerformSegueWithIdentifier(identifier: String?, sender: AnyObject?) -> Bool {
        
        if checkFieldsEmpty(){
            if let unscrambledMessage = ScramblerBrain.decryptData(textView.text, key: keyField.text){
                return true
            }
            else{
                let alert = UIAlertController(title: "Oops", message: "Wrong key entered.", preferredStyle: .Alert)
                let action = UIAlertAction(title: "Dismiss", style: UIAlertActionStyle.Cancel, handler: nil)
                alert.addAction(action)
                presentViewController(alert, animated: true, completion: nil)
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
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let dc: UnscrambledMessageViewController = (segue.destinationViewController as! UINavigationController).topViewController as! UnscrambledMessageViewController
        dc.unscrambledMessage = ScramblerBrain.decryptData(textView.text, key: keyField.text)
    }
    
    private func checkFieldsEmpty() -> Bool{
        return (textView.hasText() && keyField.hasText())
    }
    
    func bannerViewDidLoadAd(banner: ADBannerView!) {
        println("success")
        self.view.addSubview(banner) //Add banner to view (Ad loaded)
    }
    
    func bannerView(banner: ADBannerView!, didFailToReceiveAdWithError error: NSError!) {
        println("failed to load ad")
        self.canDisplayBannerAds = false
    }
}

