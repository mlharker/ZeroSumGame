using System.Collections.Generic;
using System.Linq;
using System.ComponentModel.DataAnnotations;
using DomainModel;


namespace BusShuttleManager.Models
{
    public class DriverViewModel
    {
        public int Id {get; set;}

        public string FirstName {get; set;}

        public string LastName {get; set;}

        public static DriverViewModel FromDriver(Driver driver)
        {
            return new DriverViewModel
            {
                Id = driver.Id,
                FirstName = driver.FirstName,
                LastName = driver.LastName
            };
        }
    }
}