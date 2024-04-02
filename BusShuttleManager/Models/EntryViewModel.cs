using System.Collections.Generic;
using System.Linq;
using System.ComponentModel.DataAnnotations;
using DomainModel;

namespace BusShuttleManager.Models
{
    public class EntryViewModel
    {
        public int Id { get; set;}

        public DateTime TimeStamp {get; set;}      

        public int Boarded {get; set;}

        public int LeftBehind {get; set;}  

        public static EntryViewModel FromEntry(Entry entry)
        {
            return new EntryViewModel
            {
                Id = entry.Id,
                TimeStamp = entry.TimeStamp,
                Boarded = entry.Boarded,
                LeftBehind = entry.LeftBehind
            };
        } 
    }
}