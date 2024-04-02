using DomainModel;
using Microsoft.EntityFrameworkCore;
namespace BusShuttleManager.Services
{
    public class DriverServices : IDriverService
    {
        private readonly DataContext db = new DataContext();
        List<Driver> drivers;

        public DriverServices()
        {
            db.Add(new Driver{FirstName="Jack", LastName="Carthew"});
        }


        public List<Driver> getAllDrivers()
        {
            drivers = db.Driver
                .Select(d => new Driver(d.Id, d.FirstName, d.LastName)).ToList();

            return drivers;
        }

        public Driver findDriverById(int id)
        {
            var driver = db.Driver
                .SingleOrDefault(driver =>driver.Id == id);

            return new Driver(driver);
        }

        public void UpdateDriverById(int id, string fName, string lName)
        {
            var existingDriver = db.Driver.SingleOrDefault(driver => driver.Id == id);
            existingDriver.Update(fName, lName);

            var driver = db.Driver
                .SingleOrDefault(driver => driver.Id == existingDriver.Id);
            
            if(driver != null)
            {
                driver.FirstName = fName;
                driver.LastName = lName;
                db.SaveChanges();
            }
        }

        public void CreateNewDriver(string fName, string lName)
        {
            var totalDrivers = db.Driver.Count();
            db.Add(new Driver{Id=totalDrivers+1, FirstName=fName, LastName=lName});
            db.SaveChanges();
        }
    }
}