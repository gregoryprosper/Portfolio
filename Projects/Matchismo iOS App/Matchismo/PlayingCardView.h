//
//  PlayingCardView.h
//  Matchismo
//
//  Created by Gregory Prosper on 12/11/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface PlayingCardView : UIControl
@property (nonatomic) NSUInteger rank;
@property (nonatomic,strong) NSString *suit;
@property (nonatomic) BOOL faceUp;
@end
