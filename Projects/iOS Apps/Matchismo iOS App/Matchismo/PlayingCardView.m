//
//  PlayingCardView.m
//  Matchismo
//
//  Created by Gregory Prosper on 12/11/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import "PlayingCardView.h"

@implementation PlayingCardView

#pragma mark - Properties

-(void)setRank:(NSUInteger)rank{
    _rank = rank;
    [self setNeedsDisplay];
}

-(void)setSuit:(NSString *)suit{
    _suit = suit;
    [self setNeedsDisplay];
}

-(void)setFaceUp:(BOOL)faceUp{
    _faceUp = faceUp;
    [self setNeedsDisplay];
}

#pragma mark - Drawing

#define CORNER_FONT_STANDARD_HEIGHT 130.0
#define CORNER_RADIUS 12.0

-(CGFloat)cornerScaleFactor {
    return self.bounds.size.height / CORNER_FONT_STANDARD_HEIGHT;
}

-(CGFloat)centerScaleFactor {
    return self.bounds.size.height / 75;
}

-(CGFloat)cornerRadius{
    return CORNER_RADIUS * [self cornerScaleFactor];
}

-(CGFloat)cornerOffSet {
    return [self cornerRadius] / 3.0;
}

// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    
    if (_faceUp) {
        // Drawing code
        UIBezierPath *roundedRect = [UIBezierPath bezierPathWithRoundedRect:self.bounds cornerRadius:[self cornerRadius]];
        
        [roundedRect addClip];
        
        [[UIColor whiteColor]setFill];
        
        UIRectFill(self.bounds);
        
        [[UIColor blackColor]setStroke];
        [roundedRect stroke];
        
        [self drawCorners];
        [self drawCenter];
    }
    else{
        // Drawing code
        UIBezierPath *roundedRect = [UIBezierPath bezierPathWithRoundedRect:self.bounds cornerRadius:[self cornerRadius]];
        
        [roundedRect addClip];
        
        [[UIColor whiteColor]setFill];
        
        UIRectFill(self.bounds);
        
        [[UIColor blackColor]setStroke];
        [roundedRect stroke];
        
        UIImage *brushImage = [UIImage imageNamed:@"cardBack@1x"];
        [brushImage drawInRect:roundedRect.bounds];
    }
    
    
}

-(NSString*)rankAsString{
    return @[@"?",@"A",@"2",@"3",@"4",@"5",@"6",@"7",@"8",@"9",@"10",@"J",@"Q",@"K"][self.rank];
}

-(void)drawCorners{
    
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    paragraphStyle.alignment = NSTextAlignmentCenter;
    
    UIFont *cornerFont = [UIFont preferredFontForTextStyle:UIFontTextStyleBody];
    cornerFont = [cornerFont fontWithSize:cornerFont.pointSize * [self cornerScaleFactor]];
    
    NSAttributedString *cornerText = [[NSAttributedString alloc]initWithString:[NSString stringWithFormat:@"%@\n%@",[self rankAsString],self.suit] attributes:@{ NSFontAttributeName: cornerFont, NSParagraphStyleAttributeName: paragraphStyle}];
    
    CGRect textBounds;
    textBounds.origin = CGPointMake([self cornerOffSet], [self cornerOffSet]);
    textBounds.size = [cornerText size];
    [cornerText drawInRect:textBounds];
    
    CGContextRef context = UIGraphicsGetCurrentContext();
    CGContextSaveGState(context);
    CGContextRotateCTM(context, M_PI);

    textBounds.origin = CGPointMake(-42, -62);
    textBounds.size = [cornerText size];
    [cornerText drawInRect:textBounds];
    
    CGContextRestoreGState(context);
    
}

-(void)drawCenter{
    
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    paragraphStyle.alignment = NSTextAlignmentCenter;
    
    UIFont *centerFont = [UIFont preferredFontForTextStyle:UIFontTextStyleHeadline];
    centerFont = [centerFont fontWithSize:centerFont.pointSize * [self centerScaleFactor]];
    
    NSAttributedString *cornerText = [[NSAttributedString alloc]initWithString: [NSString stringWithFormat:@"%@",self.suit] attributes:@{ NSFontAttributeName: centerFont,NSParagraphStyleAttributeName: paragraphStyle}];
    
    CGRect textBounds;
    textBounds.origin = CGPointMake((self.bounds.size.width / 2) - (cornerText.size.width / 2), (self.bounds.size.height / 2) - (cornerText.size.height / 2));
    textBounds.size = [cornerText size];
    [cornerText drawInRect:textBounds];
    
}


#pragma mark - Initialization

-(void)setup{
    self.rank = 1;
    self.suit = @"♠️";
    self.backgroundColor = nil;
    self.opaque = NO;
    self.contentMode = UIViewContentModeRedraw;
}

-(void)awakeFromNib{
    [self setup];
}

@end
