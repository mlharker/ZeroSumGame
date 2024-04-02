using DomainModel;
namespace BusShuttleManager.Services
{
    public class RouteServices : IRouteService
    {
        private readonly DataContext db = new DataContext();
        List<Routes> routess;

        public List<Routes> getAllRoutes()
        {
            routess = db.Routes
                .Select(r => new Routes(r.Id, r.Order)).ToList();
            return routess;
        }

        public Routes findRouteById(int id)
        {
            var route = db.Routes
                .SingleOrDefault(r =>r.Id == id);

            return new Routes(route);
        }

        public void UpdateRouteById(int id, int order)
        {
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

        public void CreateNewRoute(int order)
        {
            var totalRoutess = db.Routes.Count();
            db.Add(new Routes{Id = totalRoutess+1, Order=order});
            db.SaveChanges();
        }
    }
}