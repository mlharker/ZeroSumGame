using DomainModel;

namespace BusShuttleManager.Services
{
    public interface IRouteService
    {
        public List<Routes> getAllRoutes();

        public Routes findRouteById(int id);

        public void UpdateRouteById(int id, int order);

        public int GetAmountOfRoutes();

        public void CreateNewRoute(int order);
    }

}