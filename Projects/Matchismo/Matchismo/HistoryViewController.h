//
//  HistoryViewController.h
//  Matchismo
//
//  Created by Gregory Prosper on 8/13/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface HistoryViewController : UIViewController
@property (nonatomic,strong) NSMutableArray *history;
@property (nonatomic,strong) NSString* viewPerformingSegue;
@end
