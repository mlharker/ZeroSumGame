using System.Collections.Generic;
using System.Linq;
using System.ComponentModel.DataAnnotations;
using DomainModel;

namespace BusShuttleManager.Models
{
    public class RoutesViewModel
    {
        public int Id { get; set;}

        public int Order {get; set;}        

        public static RoutesViewModel FromRoutes(Routes Routes)
        {
            return new RoutesViewModel
            {
                Id = Routes.Id,
                Order = Routes.Order
            };
        } 
    }
}