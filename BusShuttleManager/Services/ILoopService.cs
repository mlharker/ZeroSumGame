using DomainModel;

namespace BusShuttleManager.Services
{
    public interface ILoopService
    {
        public List<Loop> getAllLoops();

        public Loop findLoopById(int id);

        public void UpdateLoopById(int id, string name);

        public void CreateNewLoop(string name);
    }

}