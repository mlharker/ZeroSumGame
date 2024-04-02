using DomainModel;
namespace BusShuttleManager.Services
{
    public class RouteServices : IRouteService
    {
        private DataContext db;
        List<Routes> routess;

        public List<Routes> getAllRoutes()
        {
            db = new DataContext();
            routess = db.Routes
                .Select(r => new Routes(r.Id, r.Order)).ToList();
            return routess;
        }

        public Routes findRouteById(int id)
        {
            db = new DataContext();
            var route = db.Routes
                .SingleOrDefault(r =>r.Id == id);

            return new Routes(route);
        }

        public void UpdateRouteById(int id, int order)
        {
            db = new DataContext();
            var existingRoutes = db.Routes.SingleOrDefault(Routes => Routes.Id == id);
            existingRoutes.Update(order);

            var Routes = db.Routes
                .SingleOrDefault(Routes => Routes.Id == existingRoutes.Id);
            
            if(Routes != null)
            {
                Routes.Order = order;
                db.SaveChanges();
            }
        }

        public int GetAmountOfRoutes()
        {
            db = new DataContext();
            return db.Routes.Count();
        }

        public void CreateNewRoute(int order)
        {
            db = new DataContext();
            var totalRoutess = db.Routes.Count();
            db.Add(new Routes{Id = totalRoutess, Order=order});
            db.SaveChanges();
        }
    }
}