using DomainModel;

namespace BusShuttleManager.Services
{
    public interface IEntryService
    {
        public List<Entry> getAllEntries();

        public Entry findEntryById(int id);

        public void UpdateEntryById(int id, DateTime timeStamp, int boarded, int leftBehind);

        public void CreateNewEntry(DateTime timeStamp, int boarded, int leftBehind);
    }

}