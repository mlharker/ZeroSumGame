using System.Collections.Generic;
using System.Linq;
using System.ComponentModel.DataAnnotations;
using DomainModel;


namespace BusShuttleManager.Models
{
    public class EditDriverModel
    {
       
        public int Id {get; set;}

        [StringLength(60, MinimumLength = 3)]
        public string FirstName {get; set;}

        public string LastName {get; set;}


        public static EditDriverModel FromDriver(Driver driver)
        {
            return new EditDriverModel
            {
                Id = driver.Id,
                FirstName = driver.FirstName,
                LastName = driver.LastName
            };
        }
    }
}