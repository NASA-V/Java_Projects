INSERT INTO Datalist (
                         Dataset_Name
                     )
                     VALUES (
                         'new datalist'
                     );

INSERT INTO datalist (
                         Dataset_Name
                     )
                     VALUES (
                         "Another Dataset"
                     );-- step 2
-- create new Series-- insert values into All_Series where Values(GroupName, XValue, YValue)
INSERT INTO All_Series VALUES (
                           3,
                           123456789,
                           987654321
                       );-- create new series information
-- 
INSERT INTO Series_Information (
                                   Column1_Name,
                                   Column2_Name,
                                   Series_Name,
                                   Series_Title
                               )
                               VALUES (
                                   "X_Axis",
                                   "Y_Axis",
                                   "Generic Series Name",
                                   "Generic Series Title"
                               );
                               
-- complex join that shows information on all tables

SELECT All_Series._X,
       All_Series._Y,
       Series_Information.Column1_Name,
       Series_Information.Column2_Name,
       Series_Information.[Group Name],
       Series_Information.Series_Name,
       Series_Information.Series_Title,
       Datalist.Dataset_Name
  FROM All_Series-- All_Series
       INNER JOIN
       Series_Information ON All_Series._id = Series_Information._id
       INNER JOIN
       Datalist ON Series_Information.[Group Name] = Datalist.[Group Name];-- Select Tables

SELECT *
  FROM Datalist;

SELECT *
  FROM All_Series;

SELECT *
  FROM Series_Information;
