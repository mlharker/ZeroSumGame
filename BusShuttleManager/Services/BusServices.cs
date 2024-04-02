using DomainModel;
namespace BusShuttleManager.Services
{
    public class BusServices : IBusService
    {
        private readonly DataContext db = new DataContext();
        List<Bus> busses;

        public BusServices()
        {
            db.Add(new Bus{BusName="Bus1"});
        }

        public List<Bus> getAllBusses()
        {
            busses = db.Bus
                .Select(b => new Bus(b.Id, b.BusName)).ToList();
            return busses;
        }

        public Bus findBusById(int id)
        {
            var bus = db.Bus
                .SingleOrDefault(bus =>bus.Id == id);

            return new Bus(bus);
        }

        public void UpdateBusById(int id, string name)
        {
            var existingBus = db.Bus.SingleOrDefault(bus => bus.Id == id);
            existingBus.Update(name);

            var bus = db.Bus
                .SingleOrDefault(bus => bus.Id == existingBus.Id);
            
            if(bus != null)
            {
                bus.BusName = name;
                db.SaveChanges();
            }
        }

        public void CreateNewBus(string name)
        {
            var totalBusses = db.Bus.Count();
            db.Add(new Bus{Id=totalBusses+1, BusName=name});
            db.SaveChanges();
        }
    }
}