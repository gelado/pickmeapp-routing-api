package com.getset.pickmeapp.routing.jsprit;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.getset.pickmeapp.routing.model.AlgorithmInput;
import com.getset.pickmeapp.routing.model.Car;
import com.getset.pickmeapp.routing.model.Passenger;
import com.getset.pickmeapp.routing.model.ProblemSolution;

import jsprit.analysis.toolbox.Plotter;
import jsprit.core.algorithm.VehicleRoutingAlgorithm;
import jsprit.core.algorithm.box.Jsprit;
import jsprit.core.problem.Location;
import jsprit.core.problem.VehicleRoutingProblem;
import jsprit.core.problem.VehicleRoutingProblem.FleetSize;
import jsprit.core.problem.job.Service;
import jsprit.core.problem.job.Shipment;
import jsprit.core.problem.job.Shipment.Builder;
import jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import jsprit.core.problem.solution.route.VehicleRoute;
import jsprit.core.problem.solution.route.activity.TimeWindow;
import jsprit.core.problem.vehicle.VehicleImpl;
import jsprit.core.problem.vehicle.VehicleTypeImpl;
import jsprit.core.reporting.SolutionPrinter;
import jsprit.core.reporting.SolutionPrinter.Print;
import jsprit.core.util.Solutions;

public class JSpritUtil {
	
	private static final int VEHICLE_CAPACITY = 15;
	private static final int VEHICLE_MAX_SPEED = 50;
	
	public static ProblemSolution resolveProblem(AlgorithmInput input) {

		VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance()
				.setFleetSize(FleetSize.FINITE);

		// specify type of both vehicles
		VehicleTypeImpl vehicleType = VehicleTypeImpl.Builder.newInstance("vehicleType")
				.addCapacityDimension(0, VEHICLE_CAPACITY)
				.setMaxVelocity(VEHICLE_MAX_SPEED)
				.build();

		/* add services */
		for (int i = 0; i < input.getPassengers().size(); i++) {
			
			Passenger currentPassenger = input.getPassengers().get(i);
			
			if( currentPassenger.getOrigin() != null ) {
				vrpBuilder.addJob(createPickUpAndDelivery(currentPassenger));
			} else {
				vrpBuilder.addJob(createService(currentPassenger));
			}

		}

		/* add vehicles */
		VehicleImpl vehicleImpl;

		for (int i = 0; i < input.getCars().size(); i++) {
			
			Car currentCar = input.getCars().get(i);
			
			vehicleImpl = VehicleImpl.Builder.newInstance(currentCar.getId())
					.addSkill(currentCar.getId())
					.setType(vehicleType)
					.setStartLocation(Location.newInstance(currentCar.getCoordinate().getX(), currentCar.getCoordinate().getY()))
					.setReturnToDepot(false)
					.build();

			vrpBuilder.addVehicle(vehicleImpl);
		}

		VehicleRoutingProblem problem = vrpBuilder.build();

		VehicleRoutingAlgorithm algorithm = Jsprit.createAlgorithm(problem);

		// search solutions
		Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();
		// get best
		VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);

		Collection<VehicleRoute> routes = bestSolution.getRoutes();
		
		ProblemSolution problemSolution = new ProblemSolution(routes);

		SolutionPrinter.print(problem, bestSolution, Print.VERBOSE);
		
		Date timeStamp = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		
		/* create directory for plot */ 
		String pathToPlot = "/home/gustavo/routes/";
		File file = new File(pathToPlot);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		/*
		 * plot
		 */
		new Plotter(problem, bestSolution).plot(pathToPlot + dateFormat.format(timeStamp) + ".png", "route!");
		
		return problemSolution;

	}
	
	private static Service createService(Passenger passenger) {
		
		Location delivery = Location.newInstance(passenger.getDestination().getX(), 
				passenger.getDestination().getY(), 
				passenger.getDestination().getPlaceId());
		
		jsprit.core.problem.job.Service.Builder shipment = Service.Builder.newInstance(passenger.getRequestId())
				.setName(passenger.getRequestId())
				.addSizeDimension(0, 1)
				.setLocation(delivery);
		
		return shipment.build();
		
	}
	
	private static Shipment createPickUpAndDelivery(Passenger passenger) {
		
		Location pickUp = Location.newInstance(passenger.getOrigin().getX(), 
				passenger.getOrigin().getY(), 
				passenger.getOrigin().getPlaceId());
		
		Location delivery = Location.newInstance(passenger.getDestination().getX(), 
				passenger.getDestination().getY(), 
				passenger.getDestination().getPlaceId());
		
		Builder shipment = Shipment.Builder.newInstance(passenger.getRequestId())
				.setName(passenger.getRequestId())
				.addSizeDimension(0, 1)
				.setPickupLocation(pickUp)
				.setDeliveryLocation(delivery);
		
		/* if passenger has time window */
		if(passenger.getTimeLeft() > 0) {
			shipment.setDeliveryTimeWindow(new TimeWindow(0, passenger.getTimeLeft()));
		}
		
		return shipment.build();
	}

}
