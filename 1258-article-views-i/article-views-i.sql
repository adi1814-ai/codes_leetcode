# Write your MySQL query statement below
SELECT DISTINCT author_id AS id FROM Views WHERE author_id = viewer_id ORDER BY id ASC;

 -- MY notes -- DISTINCT author_id AS id -- using AS will  change the name , DISTINCT is used to avoid duplicates in the table