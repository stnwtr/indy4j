- [ ] get this working
---
- [ ] special indy integration
- [ ] my statistics integration
- [ ] priority selection
- [ ] date parser
- [ ] update and build documentation
- [ ] release notes
- [ ] parse past events
---
````yaml
Event:
  - FutureEvent:
    - enrol(Entry): // entry (schoolday, freeroom, absence, schoolevent)
    - cancel(): // cancel enrollment
    - getEntry(int): // hour(3, 4) -> get entry or null (wrap in Optinal<T>)
  - PastEvent:
    - enrolAsAbsent(int): // hour(3, 4) -> enrolls with absent entry
````