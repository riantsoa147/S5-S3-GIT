SELECT NULL as id, NULL as date_recommandation, component_id, COUNT(id) AS recommandations
FROM components_recommandations
WHERE 1=1
  AND DATE_PART('month', date_recommandation) = 1
  AND component_id IN (
    SELECT id 
    FROM components 
    WHERE component_type_id = 1
  )
GROUP BY component_id
ORDER BY recommandations DESC;
