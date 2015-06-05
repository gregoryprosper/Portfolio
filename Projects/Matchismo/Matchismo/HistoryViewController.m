//
//  HistoryViewController.m
//  Matchismo
//
//  Created by Gregory Prosper on 8/13/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import "HistoryViewController.h"

@interface HistoryViewController ()
@property (weak, nonatomic) IBOutlet UITextView *historyTextField;

@end

@implementation HistoryViewController

-(void)viewDidLoad{
    
    if ([self.viewPerformingSegue  isEqual: @"Playing"]) {
        for (int i = (int) self.history.count - 1; i >= 0; i--) {
         self.historyTextField.text = [self.historyTextField.text stringByAppendingString:[NSString stringWithFormat:@"%@\n\n",self.history[i]]];
        }
    }else if ([self.viewPerformingSegue isEqual: @"Set"]){
        NSMutableAttributedString *holder = [[NSMutableAttributedString alloc]init];
        
        for (int i = (int) self.history.count - 1; i >= 0; i--) {
            [holder appendAttributedString:self.history[i]];
            [holder appendAttributedString:[[NSAttributedString alloc]initWithString:@"\n\n"]];
            self.historyTextField.attributedText = holder;
        }
    }
    
}

@end
