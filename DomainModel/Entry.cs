using System.ComponentModel.DataAnnotations;

namespace DomainModel
{
    public class Entry
    {
        [Key]
        public int Id {get; set;}

        public DateTime TimeStamp {get; set;}

        public int Boarded {get; set;}

        public int LeftBehind {get; set;}

        public Entry()
        {

        }

        public Entry(int id, DateTime timeStamp, int boarded, int leftBehind)
        {
            Id = id;
            TimeStamp = timeStamp;
            Boarded = boarded;
            LeftBehind = leftBehind;
        }

        public Entry(DateTime timeStamp, int boarded, int leftBehind)
        {
            TimeStamp = timeStamp;
            Boarded = boarded;
            LeftBehind = leftBehind;
        }

        public Entry(Entry entry)
        {
            Id = entry.Id;
            TimeStamp = entry.TimeStamp;
            Boarded = entry.Boarded;
            LeftBehind = entry.LeftBehind;
        }

        public void Update(DateTime timeStamp, int boarded, int leftBehind)
        {
            TimeStamp = timeStamp;
            Boarded = boarded;
            LeftBehind = leftBehind;
        }

        
    }
}

