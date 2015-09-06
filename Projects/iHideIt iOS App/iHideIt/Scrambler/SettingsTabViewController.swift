//
//  SettingsTabViewController.swift
//  iHideIt
//
//  Created by Gregory Prosper on 8/5/15.
//  Copyright (c) 2015 Prosper Inc. All rights reserved.
//

import UIKit
import iAd

class SettingsTabViewController: UIViewController, ADBannerViewDelegate {
    
    var bannerView: ADBannerView?

    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        setUpAddBanner()
    }
    
    override func viewWillDisappear(animated: Bool) {
        super.viewWillDisappear(animated)
        clearBanner()
    }
    
    func setUpAddBanner(){
        bannerView = ADBannerView(adType: .Banner)
        bannerView!.setTranslatesAutoresizingMaskIntoConstraints(false)
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

    @IBAction func shareApp(sender: UIButton) {
        let activityVc = UIActivityViewController(activityItems: ["Download iHideIt here https://appsto.re/us/GklF8.i"] , applicationActivities: nil)
        activityVc.excludedActivityTypes = [UIActivityTypePrint, UIActivityTypeAirDrop, UIActivityTypeAssignToContact, UIActivityTypeSaveToCameraRoll,UIActivityTypePostToVimeo,UIActivityTypePostToFlickr,UIActivityTypePostToTencentWeibo,UIActivityTypeAirDrop,UIActivityTypeAddToReadingList,UIActivityTypePostToWeibo]
        
        if let ppc = activityVc.popoverPresentationController{
            ppc.sourceView = sender
            ppc.sourceRect = sender.bounds
            ppc.permittedArrowDirections = UIPopoverArrowDirection.Down
        }
        presentViewController(activityVc, animated: true, completion: nil)
    }
    
    func bannerViewDidLoadAd(banner: ADBannerView!) {
        println("success")
        bannerView?.hidden = false
    }
    
    func bannerView(banner: ADBannerView!, didFailToReceiveAdWithError error: NSError!) {
        println(error.description)
        bannerView?.hidden = true
    }

}
