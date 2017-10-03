
module TidyNumbers where

reduceToTidy :: String -> String
reduceToTidy [] = []
reduceToTidy (a : as) =
  if replicate (length as) a > as then pred a : replicate (length as) '9'
  else a : reduceToTidy as


solveInput = do
  s <- reduceToTidy <$> getLine
  return $ show $ (read s :: Integer)

main = do
  x <- readLn
  flip mapM [1 .. x] $ \c -> do
    result <- solveInput
    putStr ("Case #" ++ show c ++ ": " ++ result ++ "\n")

