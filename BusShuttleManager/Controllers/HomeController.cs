using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using BusShuttleManager.Models;
using DomainModel;
using BusShuttleManager.Services;

namespace BusShuttleManager.Services;

public class HomeController : Controller 
{
    private readonly ILogger<HomeController> _logger;
    IDriverService driverService;

    IBusService busService;

    IRouteService routeService;

    IStopService stopService;

    IEntryService entryService;

    ILoopService loopService;


    public HomeController(ILogger<HomeController> logger, IDriverService dService, IBusService bService, 
        IRouteService rService, IStopService sService, IEntryService eService, ILoopService lService)
    {
        _logger = logger;
        this.driverService = dService;
        this.busService = bService;
        this.routeService = rService;
        this.stopService = sService;
        this.entryService = eService;
        this.loopService = lService;
    }

    [HttpGet("/")]
    public IActionResult Drivers()
    {
        return View(driverService.getAllDrivers().Select(d => DriverViewModel.FromDriver(d)));
    }

    [HttpGet("/CreateDriver")]
    public IActionResult CreateDriver()
    {
        var driverCreateModel = CreateDriverModel.NewDriver();
        return View(driverCreateModel);
    }

    [HttpPost]
    public IActionResult CreateDriver(int id, [Bind("FirstName,LastName")] CreateDriverModel driver)
    {
        if (ModelState.IsValid)
        {
            driverService.CreateNewDriver(driver.FirstName, driver.LastName);
            return Redirect("/");
        }

        return Redirect("/");
    }


    [HttpGet("/Busses")]
    public IActionResult Busses()
    {
        return View(busService.getAllBusses().Select(b => BusViewModel.FromBus(b)));
    }

    [HttpGet("/Routes")]
    public IActionResult Routes()
    {
        return View(routeService.getAllRoutes().Select(r => RoutesViewModel.FromRoutes(r)));
    }

    [HttpGet("/Stops")]
    public IActionResult Stops()
    {
        return View(stopService.getAllStops().Select(s=>StopViewModel.FromStop(s)));
    }

 
    [HttpGet("/Loops")]
    public IActionResult Loops()
    {
        return View(loopService.getAllLoops().Select(l => LoopViewModel.FromLoop(l)));
    }


    [HttpGet("Entries")]
    public IActionResult Entries()
    {
        return View(entryService.getAllEntries().Select(e => EntryViewModel.FromEntry(e)));
    }
}