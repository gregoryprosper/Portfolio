//
//  WeatherTableViewCell.swift
//  WeatherApp
//
//  Created by Gregory Prosper on 7/7/17.
//  Copyright © 2017 Gregory Prosper. All rights reserved.
//

import UIKit

class WeatherTableViewCell: UITableViewCell {
    @IBOutlet weak var weatherImage: UIImageView!
    @IBOutlet weak var dayLabel: UILabel!
    @IBOutlet weak var weatherDescLabel: UILabel!
    @IBOutlet weak var tempHighLabel: UILabel!
    @IBOutlet weak var tempLowLabel: UILabel!
    
    var forecast: Forecast? {
        didSet {
            tempLowLabel.text = "\(forecast!.lowTemp)"
            tempHighLabel.text = "\(forecast!.highTemp)"
            weatherDescLabel.text = forecast!.weatherType
            weatherImage.image = UIImage(named: forecast!.weatherType)
            dayLabel.text = forecast!.date
        }
    }
}
