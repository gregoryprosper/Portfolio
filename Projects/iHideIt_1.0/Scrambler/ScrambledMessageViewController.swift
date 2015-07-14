//
//  ScrambledMessageViewController.swift
//  Scrambler
//
//  Created by Gregory Prosper on 6/27/15.
//  Copyright (c) 2015 Prosper Inc. All rights reserved.
//

import UIKit
import iAd

class ScrambledMessageViewController: UIViewController, ADBannerViewDelegate {

    @IBOutlet weak var textView: UITextView!
    @IBOutlet weak var shareButton: UIButton!
    
    var scrambledMessage:String?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        textView.text = scrambledMessage
        
        let singleTap = UITapGestureRecognizer(target: self, action: Selector("textViewTapped"))
        singleTap.numberOfTapsRequired = 1
        textView.addGestureRecognizer(singleTap)
    }
    
    func textViewTapped(){
        let pb = UIPasteboard.generalPasteboard()
        pb.string = textView.text
        
        let alert = UIAlertController(title: "Copied", message: "You can now paste your message anywhere.", preferredStyle: .Alert)
        let action = UIAlertAction(title: "Dismiss", style: UIAlertActionStyle.Cancel, handler: nil)
        alert.addAction(action)
        presentViewController(alert, animated: true, completion: nil)
    }

    @IBAction func share(sender: UIButton) {
        let activityVc = UIActivityViewController(activityItems: [textView.text] , applicationActivities: nil)
        activityVc.excludedActivityTypes = [UIActivityTypePrint, UIActivityTypeAirDrop, UIActivityTypeAssignToContact, UIActivityTypeSaveToCameraRoll,UIActivityTypePostToVimeo,UIActivityTypePostToFlickr,UIActivityTypePostToTencentWeibo,UIActivityTypeAirDrop,UIActivityTypeAddToReadingList,UIActivityTypePostToWeibo]
        
        if let ppc = activityVc.popoverPresentationController{
            ppc.sourceView = shareButton
        }
        presentViewController(activityVc, animated: true, completion: nil)
    }

    @IBAction func close(sender: UIBarButtonItem) {
        self.dismissViewControllerAnimated(true, completion: nil)
    }

}
