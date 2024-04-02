using System.ComponentModel.DataAnnotations;

namespace DomainModel
{
     public class Bus
     {
          [Key]
          public int Id { get; set;}

          public string BusName {get; set;}

          public Bus()
          {
               
          }

          public Bus(int id, string name)
          {
               Id = id;
               BusName = name;
          }

          public Bus(string name)
          {
               BusName = name;
          }

          public Bus(Bus bus)
          {
               Id = bus.Id;
               BusName = bus.BusName;
          }

          public void Update(string name)
          {
               BusName = name;
          }
     }
}

