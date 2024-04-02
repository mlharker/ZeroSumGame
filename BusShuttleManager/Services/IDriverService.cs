using DomainModel;

namespace BusShuttleManager.Services
{
    public interface IDriverService
    {
        public List<Driver> getAllDrivers();

        public Driver findDriverById(int id);

        public void UpdateDriverById(int id, string fName, string lName);

        public void CreateNewDriver(string fName, string lName);
    }

}