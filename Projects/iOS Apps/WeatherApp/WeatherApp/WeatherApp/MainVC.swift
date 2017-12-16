//
//  MainVC.swift
//  WeatherApp
//
//  Created by Gregory Prosper on 7/7/17.
//  Copyright © 2017 Gregory Prosper. All rights reserved.
//

import UIKit
import CoreLocation

class MainVC: UIViewController {
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var tempLabel: UILabel!
    @IBOutlet weak var locationLabel: UILabel!
    @IBOutlet weak var weatherImage: UIImageView!
    @IBOutlet weak var weatherImageDesc: UILabel!
    @IBOutlet weak var forecastTableView: UITableView!
    @IBOutlet weak var currentWeatherActivityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var forecastActivityIndicator: UIActivityIndicatorView!
    
    fileprivate var forecasts = [Forecast]()
    let locationManager = CLLocationManager()
    
    // MARK: - Lifecycle Methods
    
    override func viewDidLoad() {
        super.viewDidLoad()
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.requestWhenInUseAuthorization()
        locationManager.startMonitoringSignificantLocationChanges()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        getLocation()
    }
    
    fileprivate func getLocation() {
        if CLLocationManager.authorizationStatus() == .authorizedWhenInUse {
            if let lat = locationManager.location?.coordinate.latitude,
                let long = locationManager.location?.coordinate.longitude {
                
                currentWeatherActivityIndicator.startAnimating()
                ApiService.shared.getCurrentWeather(lat: lat, long: long, onCompletion: {[weak self] (currentWeather) in
                    defer { self?.currentWeatherActivityIndicator.stopAnimating() }
                    guard let currentWeather = currentWeather else {
                        print("Failed to get data.")
                        return
                    }
                    
                    self?.dateLabel.text = currentWeather.date
                    self?.locationLabel.text = currentWeather.cityName
                    self?.tempLabel.text = "\(currentWeather.currentTemp)°"
                    self?.weatherImageDesc.text = currentWeather.weatherType
                    self?.weatherImage.image = UIImage(named: "\(currentWeather.weatherType)")
                })
                
                forecastActivityIndicator.startAnimating()
                ApiService.shared.getForecast(lat: lat, long: long, onCompletion: {[weak self] (forecasts) in
                    defer { self?.forecastActivityIndicator.stopAnimating() }
                    self?.forecasts = forecasts
                    self?.forecastTableView.reloadData()
                })
            }
        }
        else {
            locationManager.requestWhenInUseAuthorization()
            getLocation()
        }
    }
}

// MARK: - UITableView Data Source
extension MainVC: UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return forecasts.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as! WeatherTableViewCell
        cell.forecast = forecasts[indexPath.row]
        return cell
    }
}

// MARK: - UITableView Delegate
extension MainVC: UITableViewDelegate {
    
}
