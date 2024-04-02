using System.Collections.Generic;
using System.Linq;
using System.ComponentModel.DataAnnotations;
using DomainModel;


namespace BusShuttleManager.Models
{
    public class CreateDriverModel
    {
       
        public int Id {get; set;}

        [StringLength(60, MinimumLength = 3)]
        public string FirstName {get; set;}

        public string LastName {get; set;}

        public string Username {get; set;}

        public string Password {get; set;}

        public static CreateDriverModel NewDriver()
        {
            return new CreateDriverModel
            {
                FirstName = "",
                LastName = ""
            };
        }
    }
}