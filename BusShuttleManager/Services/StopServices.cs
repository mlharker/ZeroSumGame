using DomainModel;
namespace BusShuttleManager.Services
{
    public class StopServices : IStopService
    {
        private readonly DataContext db = new DataContext();
        List<Stop> stops;

        public List<Stop> getAllStops()
        {
            stops = db.Stop
                .Select(s => new Stop(s.Id, s.Name, s.Latitude, s.Longitude)).ToList();
            return stops;
        }

        public Stop findStopById(int id)
        {
            var stop = db.Stop
                .SingleOrDefault(s =>s.Id == id);

            return new Stop(stop);
        }

        public void UpdateStopById(int id, string name, double lat, double lon)
        {
            var existingStop = db.Stop.SingleOrDefault(stop => stop.Id == id);
            existingStop.Update(name, lat, lon);

            var stop = db.Stop
                .SingleOrDefault(stop => stop.Id == existingStop.Id);
            
            if(stop != null)
            {
                stop.Name = name;
                stop.Latitude = lat;
                stop.Longitude = lon;
                db.SaveChanges();
            }
        }

        public void CreateNewStop(string name, double lat, double lon)
        {
            var totalStops = db.Stop.Count();
            db.Add(new Stop{Id = totalStops+1, Name=name, Latitude=lat, Longitude=lon});
            db.SaveChanges();
        }
    }
}