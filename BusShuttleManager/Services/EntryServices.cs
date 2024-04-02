using DomainModel;
namespace BusShuttleManager.Services
{
    public class EntryServices : IEntryService
    {
        private readonly DataContext db = new DataContext();
        List<Entry> entries;

        public List<Entry> getAllEntries()
        {
            entries = db.Entry
                .Select(e => new Entry(e.Id, e.TimeStamp, e.Boarded, e.LeftBehind)).ToList();
            return entries;
        }

        public Entry findEntryById(int id)
        {
            var entry = db.Entry
                .SingleOrDefault(e =>e.Id == id);

            return new Entry(entry);
        }

        public void UpdateEntryById(int id, DateTime timeStamp, int boarded, int leftBehind)
        {
            var existingEntry = db.Entry.SingleOrDefault(entry => entry.Id == id);
            existingEntry.Update(timeStamp, boarded, leftBehind);

            var entry = db.Entry
                .SingleOrDefault(entry => entry.Id == existingEntry.Id);
            
            if(entry != null)
            {
                entry.TimeStamp = timeStamp;
                entry.Boarded = boarded;
                entry.LeftBehind = leftBehind;
                db.SaveChanges();
            }
        }

        public void CreateNewEntry(DateTime timeStamp, int boarded, int leftBehind)
        {
            var totalEntries = db.Entry.Count();
            db.Add(new Entry{Id = totalEntries+1, TimeStamp=timeStamp, Boarded=boarded, LeftBehind=leftBehind});
            db.SaveChanges();
        }
    }
}